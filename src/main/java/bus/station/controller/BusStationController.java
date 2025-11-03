package bus.station.controller;


import bus.station.model.BusStation;
import bus.station.service.BusStationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/bus-station")
public class BusStationController {
    private final BusStationService busStationService;

    public BusStationController(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @RequestMapping
    public String findAll(Model model) {
        model.addAttribute("busStations", busStationService.findAll());
        return"bus-station/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busStation", new BusStation());
        return "bus-station/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<BusStation> busStationOptional = busStationService.findById(id);
        if (busStationOptional.isPresent()) {
            model.addAttribute("busStation", busStationOptional.get());
            return "bus-station/form";
        }
        return "redirect:/bus-station";
    }

    @PostMapping
    public String createOrUpdateBusStation(@ModelAttribute BusStation busStation) {
        busStationService.save(busStation);
        return "redirect:/bus-station";
    }

    @PostMapping("{id}/delete")
    public String deleteBusStation(@PathVariable String id) {
        busStationService.deleteById(id);
        return "redirect:/bus-station";
    }
}
