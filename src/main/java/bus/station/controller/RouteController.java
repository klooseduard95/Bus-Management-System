package bus.station.controller;

import bus.station.model.BusStation;
import bus.station.model.Route;
import bus.station.service.BusStationService;
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
    private final BusStationService busStationService;

    public RouteController(RouteService routeService, BusStationService busStationService) {
        this.routeService = routeService;
        this.busStationService = busStationService;
    }

    private void addBusStationsToModel(Model model) {
        List<BusStation> stations = busStationService.findAll();
        model.addAttribute("allBusStations", stations);
    }

    @GetMapping
    public String getRouteList(Model model) {
        model.addAttribute("routes", routeService.findAll());
        model.addAttribute("activePage", "route");
        return "route/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("route", new Route());
        addBusStationsToModel(model);
        model.addAttribute("activePage", "route");
        return "route/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Route> routeOptional = routeService.findById(id);

        if (routeOptional.isPresent()) {
            model.addAttribute("route", routeOptional.get());
            addBusStationsToModel(model);
            model.addAttribute("activePage", "route");
            return "route/form";
        } else {
            return "redirect:/route";
        }
    }

    @PostMapping
    public String createOrUpdateRoute(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam("originId") Long originId,
            @RequestParam("destinationId") Long destinationId,
            @RequestParam("distance") double distance,
            Model model) {

        try {
            BusStation origin = busStationService.findById(originId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Origin ID"));
            BusStation destination = busStationService.findById(destinationId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Destination ID"));

            if (originId.equals(destinationId)) {
                throw new IllegalArgumentException("Origin and Destination cannot be the same.");
            }

            Route route;
            if (id != null) {
                route = routeService.findById(id).orElse(new Route());
            } else {
                route = new Route();
            }

            route.setOrigin(origin);
            route.setDestination(destination);
            route.setDistance(distance);

            routeService.save(route);
            return "redirect:/route";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());

            Route route = new Route();
            route.setId(id);
            route.setDistance(distance);
            model.addAttribute("route", route);

            addBusStationsToModel(model);
            model.addAttribute("activePage", "route");
            return "route/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteById(id);
        return "redirect:/route";
    }

    @GetMapping("/{id}")
    public String getRouteDetails(@PathVariable Long id, Model model) {
        Optional<Route> routeOpt = routeService.findById(id);

        if (routeOpt.isPresent()) {
            model.addAttribute("route", routeOpt.get());
            model.addAttribute("activePage", "route");
            return "route/details";
        }
        return "redirect:/route";
    }

}
