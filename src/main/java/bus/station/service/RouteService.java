package bus.station.service;

import bus.station.model.Route;
import bus.station.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public Route save(Route route) {
        if (route.getOrigin() != null && route.getOrigin().equals(route.getDestination())) {
            throw new IllegalArgumentException("Origin and Destination cannot be the same.");
        }
        return routeRepository.save(route);
    }

    public Optional<Route> findById(String id) {
        return routeRepository.findById(id);
    }

    public void deleteById(String id) {
        routeRepository.deleteById(id);
    }
}
