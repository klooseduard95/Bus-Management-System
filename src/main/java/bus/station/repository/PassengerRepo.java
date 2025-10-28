package bus.station.repository;

import bus.station.model.Passenger;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerRepo extends InMemoryRepo<Passenger> {
}
