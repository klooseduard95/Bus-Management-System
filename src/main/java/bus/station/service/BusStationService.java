package bus.station.service;

import bus.station.model.BusStation;
import bus.station.repository.BusStationRepository;
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
public class BusStationService {
    private final BusStationRepository busStationRepository;

    @Autowired
    public BusStationService(BusStationRepository busStationRepository) {
        this.busStationRepository = busStationRepository;
    }

    public List<BusStation> findAll() {
        return busStationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<BusStation> findAll(String name, String city, String sortField, String sortDir) {

        Specification<BusStation> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(name)) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (StringUtils.hasText(city)) {
                predicates.add(cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = Sort.by(sortField);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return busStationRepository.findAll(spec, sort);
    }

    @Transactional
    public BusStation save(BusStation busStation) {
        if (busStation == null) {
            throw new IllegalArgumentException("BusStation object cannot be null.");
        }

        String name = busStation.getName();
        String city = busStation.getCity();

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Bus station name must not be null or empty.");
        }
        if (name.length() < 3 || name.length() > 100) {
            throw new IllegalArgumentException("Bus station name must be between 3 and 100 characters.");
        }

        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Bus station city must not be null or empty.");
        }
        if (city.length() < 3 || city.length() > 100) {
            throw new IllegalArgumentException("Bus station city must be between 3 and 100 characters.");
        }

        if (busStation.getId() == null && busStationRepository.existsByName(name)) {
            throw new IllegalArgumentException("Bus station with this name '" + name + "' already exists!");
        }

        return busStationRepository.save(busStation);
    }

    public Optional<BusStation> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bus Station ID must be non-null and positive.");
        }
        return busStationRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bus Station ID must be non-null and positive.");
        }

        Optional<BusStation> busStationOpt = busStationRepository.findById(id);

        if (busStationOpt.isPresent()) {
            BusStation busStation = busStationOpt.get();

            boolean isOrigin = busStation.getRoutesAsOrigin() != null && !busStation.getRoutesAsOrigin().isEmpty();
            boolean isDest = busStation.getRoutesAsDestination() != null && !busStation.getRoutesAsDestination().isEmpty();

            if (isOrigin || isDest) {
                throw new RuntimeException("Cannot delete Bus Station. It is assigned to existing routes.");
            }
            busStationRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Bus Station with ID " + id + " not found for deletion.");
        }
    }
}