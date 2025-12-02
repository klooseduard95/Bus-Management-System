package bus.station.service;

import bus.station.model.Route;
import bus.station.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Route save(Route route) {
        if (route == null) {
            throw new IllegalArgumentException("Route object cannot be null.");
        }

        if (route.getOrigin() == null || route.getOrigin().getId() == null) {
            throw new IllegalArgumentException("Origin station must be valid (non-null and must have an ID).");
        }

        if (route.getDestination() == null || route.getDestination().getId() == null) {
            throw new IllegalArgumentException("Destination station must be valid (non-null and must have an ID).");
        }

        if (route.getOrigin().getId().equals(route.getDestination().getId())) {
            throw new IllegalArgumentException("Origin and Destination cannot be the same.");
        }

        if (route.getDistance() == 0) {
            throw new IllegalArgumentException("Distance must be greater than 0.");
        }

        if (route.getDistance() < 0) {
            throw new IllegalArgumentException("Distance must be a positive number.");
        }

        if (route.getId() == null && routeRepository.existsByOriginIdAndDestinationId(route.getOrigin().getId(), route.getDestination().getId())) {
            throw new IllegalArgumentException("A route between this Origin and Destination already exists.");
        }

        return routeRepository.save(route);
    }

    public Optional<Route> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Route ID must be non-null and positive.");
        }
        return routeRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Route ID must be non-null and positive.");
        }

        Optional<Route> routeOpt = routeRepository.findById(id);

        if (routeOpt.isPresent()) {
            Route route = routeOpt.get();

            if (route.getTrips() != null && !route.getTrips().isEmpty()) {
                throw new RuntimeException("Cannot delete route. It is associated with existing bus trips.");
            }

            routeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Route with ID " + id + " not found for deletion.");
        }
    }
}