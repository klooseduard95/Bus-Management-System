package bus.station.repository;

import bus.station.model.Passenger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerRepository extends JsonFileRepository<Passenger> {

    public PassengerRepository(ObjectMapper objectMapper) {
        super("data/passenger", objectMapper, new TypeReference<>() {});
    }
}
