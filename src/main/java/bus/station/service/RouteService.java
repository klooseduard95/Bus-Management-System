package bus.station.service;

import bus.station.model.Route;
import bus.station.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private final RouteRepo  routeRepo;
    @Autowired
    public RouteService(RouteRepo  routeRepo) {
        this.routeRepo = routeRepo;
    }

    public Optional<Route> findByID(String id) {
        return routeRepo.findById(id);
    }

    public List<Route> findAll() {
        return routeRepo.findAll();
    }
}
