package bus.station.controller;

import bus.station.model.Bus;
import bus.station.model.BusTrip;
import bus.station.service.BusService;
import bus.station.service.BusTripService;
import bus.station.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        if (bindingResult.hasErrors()) {
            addRoutesAndBusesToModel(model);
            model.addAttribute("activePage", "bus-trip");
            return "bus-trip/form";
        }

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
    public String deleteBusTrip(@PathVariable Long id) {
        busTripService.deleteById(id);
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
