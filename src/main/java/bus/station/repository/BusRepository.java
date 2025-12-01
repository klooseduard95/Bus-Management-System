package bus.station.repository;

import bus.station.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByRegistrationNumber(String registrationNumber);
}
