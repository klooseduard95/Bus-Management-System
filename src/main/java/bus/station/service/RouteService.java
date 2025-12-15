package bus.station.service;

import bus.station.model.Route;
import bus.station.repository.RouteRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public List<Route> findAll(String originCity, String destCity, Double maxDistance, String sortField, String sortDir) {

        Specification<Route> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(originCity)) {
                Join<Object, Object> originJoin = root.join("origin");
                predicates.add(cb.like(cb.lower(originJoin.get("city")), "%" + originCity.toLowerCase() + "%"));
            }

            if (StringUtils.hasText(destCity)) {
                Join<Object, Object> destJoin = root.join("destination");
                predicates.add(cb.like(cb.lower(destJoin.get("city")), "%" + destCity.toLowerCase() + "%"));
            }

            if (maxDistance != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("distance"), maxDistance));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = Sort.by(sortField);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return routeRepository.findAll(spec, sort);
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