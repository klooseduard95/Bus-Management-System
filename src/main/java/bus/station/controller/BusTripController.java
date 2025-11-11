package bus.station.controller;

import bus.station.model.BusTrip;
import bus.station.repository.BusRepository;
import bus.station.repository.RouteRepository;
import bus.station.service.BusTripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/bus-trip")
public class BusTripController {
    private final BusTripService busTripService;
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;


    public BusTripController(BusTripService busTripService, RouteRepository routeRepository, BusRepository busRepository) {
        this.busTripService = busTripService;
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
    }
    private void addRoutesAndBusesToModel(Model model) {
        model.addAttribute("allRoutes", routeRepository.findAll());
        model.addAttribute("allBuses", busRepository.findAll());
    }

    @GetMapping
    public String getBusTripList(Model model) {
        model.addAttribute("busTrips", busTripService.findAll());
        return "bus-trip/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("busTrip", new BusTrip());
        addRoutesAndBusesToModel(model);
        return "bus-trip/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<BusTrip> busTripOptional = busTripService.findById(id);
        if (busTripOptional.isPresent()) {
            model.addAttribute("busTrip", busTripOptional.get());
            addRoutesAndBusesToModel(model);
            return "bus-trip/form";
        } else {
            return "redirect:/bus-trip";
        }
    }

    @PostMapping
    public String createOrUpdateBusTrip(@ModelAttribute BusTrip busTrip) {
        busTripService.save(busTrip);
        return "redirect:/bus-trip";
    }

    @PostMapping("/{id}/delete")
    public String deleteBusTrip(@PathVariable String id) {
        busTripService.deleteById(id);
        return "redirect:/bus-trip";
    }
}
