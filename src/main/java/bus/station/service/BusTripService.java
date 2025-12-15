package bus.station.service;

import bus.station.enums.BusTripStatus;
import bus.station.model.BusTrip;
import bus.station.repository.BusTripRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusTripService {
    private final BusTripRepository busTripRepository;

    @Autowired
    public BusTripService(BusTripRepository busTripRepository) {
        this.busTripRepository = busTripRepository;
    }

    public List<BusTrip> findAll() {
        return busTripRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<BusTrip> findAll(LocalTime startTimeFrom,
                                 LocalTime startTimeTo,
                                 BusTripStatus status,
                                 Double maxPrice,
                                 String sortField,
                                 String sortDir) {

        Specification<BusTrip> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (startTimeFrom != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("startTime"), startTimeFrom));
            }
            if (startTimeTo != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("startTime"), startTimeTo));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("basePrice"), maxPrice));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = Sort.by(sortField);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return busTripRepository.findAll(spec, sort);
    }

    @Transactional
    public BusTrip save(BusTrip busTrip) {
        if (busTrip == null) {
            throw new IllegalArgumentException("BusTrip object cannot be null.");
        }

        if (busTrip.getBus() == null || busTrip.getBus().getId() == null) {
            throw new IllegalArgumentException("Bus must be valid (non-null and must have an ID).");
        }
        if (busTrip.getRoute() == null || busTrip.getRoute().getId() == null) {
            throw new IllegalArgumentException("Route must be valid (non-null and must have an ID).");
        }

        if (busTrip.getAvailableSeats() == 0 || busTrip.getAvailableSeats() < 0) {
            throw new IllegalArgumentException("Available seats must be non-negative.");
        }
        if (busTrip.getBasePrice() == 0|| busTrip.getBasePrice() <= 0 ) {
            throw new IllegalArgumentException("Base price must be positive (greater than 0).");
        }

        if (busTrip.getStartTime() == null) {
            throw new IllegalArgumentException("Departure time must not be null.");
        }
        if (busTrip.getStartTime().isBefore(LocalTime.now())) {
            if (busTrip.getId() == null) {
                throw new IllegalArgumentException("Departure time must be in the future for a new trip.");
            }
        }

        return busTripRepository.save(busTrip);
    }

    public Optional<BusTrip> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bus Trip ID must be non-null and positive.");
        }
        return busTripRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bus Trip ID must be non-null and positive.");
        }

        Optional<BusTrip> busTripOpt = busTripRepository.findById(id);

        if(busTripOpt.isPresent()) {
            BusTrip busTrip = busTripOpt.get();

            if(busTrip.getTickets() != null && !busTrip.getTickets().isEmpty()) {
                throw new RuntimeException("Cannot delete Trip. It has issued tickets.");
            }

            busTripRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Bus Trip with ID " + id + " not found for deletion.");
        }
    }
}