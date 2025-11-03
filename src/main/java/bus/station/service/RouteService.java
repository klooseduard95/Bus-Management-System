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

    public List<Route> findAll() {
        return routeRepo.findAll();
    }

    public Route save(Route route) {
        if (route.getOrigin() != null && route.getOrigin().equals(route.getDestination())) {
            throw new IllegalArgumentException("Origin and Destination cannot be the same.");
        }
        return routeRepo.save(route);
    }

    public Optional<Route> findById(String id) {
        return routeRepo.findById(id);
    }

    public void deleteById(String id) {
        routeRepo.deleteById(id);
    }
}
