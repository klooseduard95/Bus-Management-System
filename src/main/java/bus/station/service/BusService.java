package bus.station.service;

import bus.station.enums.BusStatus;
import bus.station.model.Bus;
import bus.station.repository.BusRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Bus> findAll(String registrationNumber,
                             BusStatus status,
                             Integer minCapacity,
                             String sortField,
                             String sortDir) {

        Specification<Bus> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(registrationNumber)) {
                predicates.add(cb.like(cb.lower(root.get("registrationNumber")), "%" + registrationNumber.toLowerCase() + "%"));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (minCapacity != null) {
                predicates.add(cb.ge(root.get("capacity"), minCapacity));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = Sort.by(sortField);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return busRepository.findAll(spec, sort);
    }

    public Optional<Bus> findBusById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bus ID must be non-null and positive.");
        }
        return busRepository.findById(id);
    }


    @Transactional
    public Bus save(Bus bus) {
        if (bus == null) {
            throw new IllegalArgumentException("Bus object cannot be null.");
        }

        if (bus.getRegistrationNumber() == null || bus.getRegistrationNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Bus registration number cannot be null or empty.");
        }

        if (bus.getId() == null && busRepository.existsByRegistrationNumber(bus.getRegistrationNumber())) {
            throw new IllegalArgumentException("Bus with registration number " + bus.getRegistrationNumber() + " already exists!");
        }

        if (bus.getCapacity() == 0 || bus.getCapacity() <= 0) {
            throw new IllegalArgumentException("Bus capacity must be positive (greater than 0).");
        }

        if (bus.getLastMaintenanceDate() == null) {
            throw new IllegalArgumentException("Bus last maintenance date must not be null.");
        }
        if (bus.getLastMaintenanceDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Bus last maintenance date must be past or present!");
        }

        return busRepository.save(bus);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bus ID must be non-null and positive.");
        }

        Optional<Bus> busOpt = busRepository.findById(id);

        if (busOpt.isPresent()) {
            Bus bus = busOpt.get();

            if (bus.getBusTrips() != null && !bus.getBusTrips().isEmpty()) {
                throw new RuntimeException("Cannot delete bus. It is assigned to existing trips.");
            }

            busRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Bus with ID " + id + " not found for deletion.");
        }
    }
}