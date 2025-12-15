package bus.station.service;

import bus.station.enums.Role;
import bus.station.model.DutyAssignment;
import bus.station.repository.DutyAssignmentRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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
public class DutyAssignmentService {

    private final DutyAssignmentRepository dutyAssignmentRepository;

    @Autowired
    public DutyAssignmentService(DutyAssignmentRepository dutyAssignmentRepository) {
        this.dutyAssignmentRepository = dutyAssignmentRepository;
    }

    public List<DutyAssignment> findAll() {
        return dutyAssignmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<DutyAssignment> findAll(Long tripId,
                                        Role role,
                                        String driverName,
                                        String sortField,
                                        String sortDir) {

        Specification<DutyAssignment> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tripId != null) {
                predicates.add(cb.equal(root.get("busTrip").get("id"), tripId));
            }

            if (role != null) {
                predicates.add(cb.equal(root.get("role"), role));
            }

            if (StringUtils.hasText(driverName)) {
                Join<Object, Object> driverJoin = root.join("driver", JoinType.LEFT);
                predicates.add(cb.like(cb.lower(driverJoin.get("name")), "%" + driverName.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = Sort.by(sortField);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return dutyAssignmentRepository.findAll(spec, sort);
    }

    @Transactional
    public DutyAssignment save(DutyAssignment dutyAssignment) {
        if (dutyAssignment == null) {
            throw new IllegalArgumentException("DutyAssignment object cannot be null.");
        }

        if (dutyAssignment.getBusTrip() == null || dutyAssignment.getBusTrip().getId() == null) {
            throw new IllegalArgumentException("Bus Trip must be valid (non-null and must have an ID).");
        }

        boolean hasDriver = dutyAssignment.getDriver() != null && dutyAssignment.getDriver().getId() != null;
        boolean hasManager = dutyAssignment.getManager() != null && dutyAssignment.getManager().getId() != null;

        if (!hasDriver && !hasManager) {
            throw new IllegalArgumentException("An assignment must have either a Driver or a Trip Manager.");
        }

        if (hasDriver && hasManager) {
            throw new IllegalArgumentException("Cannot assign both a Driver and a Manager in the same assignment entry.");
        }

        if (dutyAssignment.getId() == null) {
            Long tripId = dutyAssignment.getBusTrip().getId();

            if (hasDriver) {
                if (dutyAssignmentRepository.existsByBusTripIdAndDriverId(tripId, dutyAssignment.getDriver().getId())) {
                    throw new IllegalArgumentException("Driver is already assigned to this Bus Trip.");
                }
                if (dutyAssignmentRepository.existsByBusTripIdAndDriverIsNotNull(tripId)) {
                    throw new IllegalArgumentException("This Bus Trip already has an assigned Driver.");
                }
            }

            if (hasManager) {
                if (dutyAssignmentRepository.existsByBusTripIdAndManagerId(tripId, dutyAssignment.getManager().getId())) {
                    throw new IllegalArgumentException("Manager is already assigned to this Bus Trip.");
                }
                if (dutyAssignmentRepository.existsByBusTripIdAndManagerIsNotNull(tripId)) {
                    throw new IllegalArgumentException("This Bus Trip already has an assigned Manager.");
                }
            }
        }

        return dutyAssignmentRepository.save(dutyAssignment);
    }

    public Optional<DutyAssignment> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Duty Assignment ID must be non-null and positive.");
        }
        return dutyAssignmentRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Duty Assignment ID must be non-null and positive.");
        }

        if (!dutyAssignmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Duty Assignment with ID " + id + " not found for deletion.");
        }

        dutyAssignmentRepository.deleteById(id);
    }
}