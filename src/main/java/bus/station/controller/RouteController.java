package bus.station.controller;

import bus.station.model.BusStation;
import bus.station.model.Route;
import bus.station.service.BusStationService;
import bus.station.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String getRouteList(
            @RequestParam(required = false) String originCity,
            @RequestParam(required = false) String destCity,
            @RequestParam(required = false) Double maxDistance,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        model.addAttribute("routes", routeService.findAll(originCity, destCity, maxDistance, sortField, sortDir));

        model.addAttribute("originCity", originCity);
        model.addAttribute("destCity", destCity);
        model.addAttribute("maxDistance", maxDistance);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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
    public String createOrUpdateRoute(@Valid @ModelAttribute Route route, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            addBusStationsToModel(model);
            model.addAttribute("activePage",  "route");
            return  "route/form";
        }
        try {
            routeService.save(route);
        } catch (Exception e) {
            model.addAttribute("globalError", e.getMessage());
            addBusStationsToModel(model);
            model.addAttribute("activePage", "route");
            return "route/form";
        }
        return "redirect:/route";
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
