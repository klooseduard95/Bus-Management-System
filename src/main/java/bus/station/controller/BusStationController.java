package bus.station.controller;

import bus.station.model.BusStation;
import bus.station.service.BusStationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Import needed

import java.util.Optional;

@Controller
@RequestMapping("/bus-station")
public class BusStationController {
    private final BusStationService busStationService;

    public BusStationController(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("busStations", busStationService.findAll());
        model.addAttribute("activePage", "bus-station");
        return "bus-station/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busStation", new BusStation());
        model.addAttribute("activePage", "bus-station");
        return "bus-station/form";
    }

    @PostMapping
    public String createOrUpdateBus(@Valid @ModelAttribute BusStation busStation, BindingResult bindingResult, Model model) {
        // 1. Validation Errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "bus-station");
            return "bus-station/form";
        }

        // 2. Business Logic Errors (Exceptions)
        try {
            busStationService.save(busStation);
        } catch (Exception e) {
            model.addAttribute("globalError", e.getMessage());
            model.addAttribute("activePage", "bus-station");
            return "bus-station/form";
        }

        return "redirect:/bus-station";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<BusStation> busStationOptional = busStationService.findById(id);
        if (busStationOptional.isPresent()) {
            model.addAttribute("busStation", busStationOptional.get());
            model.addAttribute("activePage", "bus-station");
            return "bus-station/form";
        }
        return "redirect:/bus-station";
    }

    @PostMapping("/{id}/delete")
    public String deleteBusStation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            busStationService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Station deleted successfully.");
        } catch (RuntimeException e) {
            // Sends the error to the index page
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/bus-station";
    }

    @GetMapping("/{id}")
    public String getBusStationDetails(@PathVariable Long id, Model model) {
        Optional<BusStation> stationOpt = busStationService.findById(id);
        if (stationOpt.isPresent()) {
            model.addAttribute("station", stationOpt.get());
            model.addAttribute("activePage", "bus-station");
            return "bus-station/details";
        }
        return "redirect:/bus-station";
    }
}