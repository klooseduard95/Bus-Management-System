package bus.station.controller;

import bus.station.model.Bus;
import bus.station.service.BusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "bus/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus/form";
    }

    @PostMapping("{id}/delete")
    public String deleteBus(@PathVariable String id) {
        busService.deleteById(id);

        return "redirect:/bus";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Bus> busOptional = busService.findBusById(id);

        if (busOptional.isPresent()) {
            model.addAttribute("bus", busOptional.get());
            return "bus/form";
        } else {
            return "redirect:/bus";
        }
    }

    @PostMapping
    public String createOrUpdateBus(@ModelAttribute Bus bus) {
        busService.save(bus);
        return "redirect:/bus";
    }

}
