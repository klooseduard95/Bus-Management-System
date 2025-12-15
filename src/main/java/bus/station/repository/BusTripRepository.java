package bus.station.repository;

import bus.station.enums.BusTripStatus;
import bus.station.model.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Long>, JpaSpecificationExecutor<BusTrip> {
    List<BusTrip> findAllByStatus(BusTripStatus status);
}
