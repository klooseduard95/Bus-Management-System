package bus.station.repository;

import bus.station.model.DutyAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyAssignmentRepository extends JpaRepository<DutyAssignment, Long> {
    boolean existsByBusTripIdAndDriverId(Long tripId, Long id);

    boolean existsByBusTripIdAndDriverIsNotNull(Long tripId);

    boolean existsByBusTripIdAndManagerId(Long tripId, Long id);

    boolean existsByBusTripIdAndManagerIsNotNull(Long tripId);
}
