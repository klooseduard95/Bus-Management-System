package bus.station.repository;

import bus.station.model.BusStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusStationRepository extends JpaRepository<BusStation, Long> {
    boolean existsByName(String name);
}
