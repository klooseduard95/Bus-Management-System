package bus.station.controller;

import bus.station.model.Route;
import bus.station.repository.RouteRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private RouteRepo routeRepo;

    public RouteController(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    @GetMapping ("/{id}")
    public Optional<Route>  findById(@PathVariable String id) {
        return routeRepo.findById(id);
    }

    @GetMapping("/")
    public List<Route> findAll() {
        return routeRepo.findAll();
    }


}
