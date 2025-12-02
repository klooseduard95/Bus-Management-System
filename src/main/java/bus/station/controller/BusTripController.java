package bus.station.controller;

import bus.station.model.BusTrip;
import bus.station.service.BusService;
import bus.station.service.BusTripService;
import bus.station.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Needed for delete errors

import java.util.Optional;

@Controller
@RequestMapping("/bus-trip")
public class BusTripController {
    private final BusTripService busTripService;
    private final RouteService routeService;
    private final BusService busService;

    public BusTripController(BusTripService busTripService,
                             RouteService routeService,
                             BusService busService) {
        this.busTripService = busTripService;
        this.routeService = routeService;
        this.busService = busService;
    }

    private void addRoutesAndBusesToModel(Model model) {
        model.addAttribute("allRoutes", routeService.findAll());
        model.addAttribute("allBuses", busService.findAll());
    }

    @GetMapping
    public String getBusTripList(Model model) {
        model.addAttribute("busTrips", busTripService.findAll());
        model.addAttribute("activePage", "bus-trip");
        return "bus-trip/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busTrip", new BusTrip());
        addRoutesAndBusesToModel(model);
        model.addAttribute("activePage", "bus-trip");
        return "bus-trip/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<BusTrip> busTripOptional = busTripService.findById(id);

        if (busTripOptional.isPresent()) {
            model.addAttribute("busTrip", busTripOptional.get());
            addRoutesAndBusesToModel(model);
            model.addAttribute("activePage", "bus-trip");
            return "bus-trip/form";
        } else {
            return "redirect:/bus-trip";
        }
    }

    @PostMapping
    public String createOrUpdateBus(@Valid @ModelAttribute BusTrip busTrip, BindingResult bindingResult, Model model) {
        // 1. Handle Validation Errors (@NotNull, @Min, etc.)
        if (bindingResult.hasErrors()) {
            addRoutesAndBusesToModel(model);
            model.addAttribute("activePage", "bus-trip");
            return "bus-trip/form";
        }

        // 2. Handle Logic Errors (Exceptions)
        try {
            busTripService.save(busTrip);
        } catch (RuntimeException e) {
            model.addAttribute("globalError", e.getMessage());
            addRoutesAndBusesToModel(model);
            model.addAttribute("activePage", "bus-trip");
            return "bus-trip/form";
        }

        return "redirect:/bus-trip";
    }

    @PostMapping("/{id}/delete")
    public String deleteBusTrip(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            busTripService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Trip deleted successfully.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/bus-trip";
    }

    @GetMapping("/{id}")
    public String getBusTripDetails(@PathVariable Long id, Model model) {
        Optional<BusTrip> tripOpt = busTripService.findById(id);

        if (tripOpt.isPresent()) {
            model.addAttribute("trip", tripOpt.get());
            model.addAttribute("activePage", "bus-trip");
            return "bus-trip/details";
        }
        return "redirect:/bus-trip";
    }
}