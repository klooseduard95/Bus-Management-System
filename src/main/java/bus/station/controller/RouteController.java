package bus.station.controller;

import bus.station.model.Route;
import bus.station.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routes")
public class RouteController {
    private RouteRepo routeRepo;

    public RouteController(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    @RequestMapping ("/{id}")
    public Optional<Route>  findById(@PathVariable String id) {
        return routeRepo.findById(id);
    }

    @RequestMapping("/")
    public List<Route> findAll() {
        return routeRepo.findAll();
    }


}
