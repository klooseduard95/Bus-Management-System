package bus.station.controller;

import bus.station.model.Bus;
import bus.station.service.BusService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bus")
public class BusController {
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public String getBusList(Model model) {
        model.addAttribute("buses", busService.findAll());
        model.addAttribute("activePage", "bus");
        return "bus/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("activePage", "bus");
        return "bus/form";
    }

    @PostMapping
    public String createOrUpdateBus(@Valid @ModelAttribute Bus bus,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "bus");
            return "bus/form";
        }

        try {
            busService.save(bus);
        } catch (Exception e) {
            // Prindem eroarea de unicitate (Registration Number)
            model.addAttribute("globalError", e.getMessage());
            model.addAttribute("activePage", "bus");
            return "bus/form";
        }

        return "redirect:/bus";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Bus> busOptional = busService.findBusById(id);

        if (busOptional.isPresent()) {
            model.addAttribute("bus", busOptional.get());
            model.addAttribute("activePage", "bus");
            return "bus/form";
        } else {
            return "redirect:/bus";
        }
    }

    @PostMapping("{id}/delete")
    public String deleteBus(@PathVariable Long id) {
        busService.deleteById(id);
        return "redirect:/bus";
    }

    @GetMapping("/{id}")
    public String getBusDetails(@PathVariable Long id, Model model) {
        Optional<Bus> busOptional = busService.findBusById(id);

        if (busOptional.isPresent()) {
            model.addAttribute("bus", busOptional.get());
            model.addAttribute("activePage", "bus");
            return "bus/details";
        } else {
            return "redirect:/bus";
        }
    }

}
