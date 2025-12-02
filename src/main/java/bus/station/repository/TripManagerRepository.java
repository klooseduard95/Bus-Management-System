package bus.station.repository;

import bus.station.model.TripManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripManagerRepository extends JpaRepository<TripManager, Long> {
    boolean existsByEmployeeCode(String employeeCode);
}