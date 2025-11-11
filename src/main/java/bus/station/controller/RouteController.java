package bus.station.controller;

import bus.station.model.BusStation;
import bus.station.model.Route;
import bus.station.repository.BusStationRepository;
import bus.station.repository.RouteRepo;
import bus.station.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;
    private final RouteRepo routeRepo;
    private final BusStationRepository busStationRepository;

    public RouteController(RouteService routeService, RouteRepo routeRepo, BusStationRepository busStationRepository) {
        this.routeService = routeService;
        this.routeRepo = routeRepo;
        this.busStationRepository = busStationRepository;
    }

    private void addBusStationsToModel(Model model) {
        List<BusStation> stations = busStationRepository.findAll();
        model.addAttribute("allBusStations", stations);
    }

    @GetMapping
    public String getRouteList(Model model) {
        model.addAttribute("routes", routeService.findAll());
        return "route/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("route", new Route());
        addBusStationsToModel(model);
        return "route/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Route> routeOptional = routeService.findById(id);
        if (routeOptional.isPresent()) {
            model.addAttribute("route", routeOptional.get());
            addBusStationsToModel(model);
            return "route/form";
        } else {
            return "redirect:/route";
        }
    }

    @PostMapping
    public String createOrUpdateRoute(@RequestParam(value = "id", required = false) String id,
                                      @RequestParam("originId") String originId,
                                      @RequestParam("destinationId") String destinationId,
                                      @RequestParam("distance") double distance) {

        BusStation origin = busStationRepository.findById(originId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Origin Station ID: " + originId));
        BusStation destination = busStationRepository.findById(destinationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Destination Station ID: " + destinationId));

        Route route;
        if (id != null && !id.isEmpty()) {
            route = routeService.findById(id).orElse(new Route());
        } else {
            route = new Route();
        }

        route.setOrigin(origin);
        route.setDestination(destination);
        route.setDistance(distance);

        routeService.save(route);
        return "redirect:/route";
    }

    @PostMapping("/{id}/delete")
    public String deleteRoute(@PathVariable String id) {
        routeService.deleteById(id);
        return "redirect:/route";
    }

}
