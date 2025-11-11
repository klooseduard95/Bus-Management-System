package bus.station.repository;

import bus.station.model.BusTrip;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BusTripRepository extends JsonFileRepository<BusTrip> {

    public BusTripRepository(ObjectMapper objectMapper) {
        super("data/bus-trips.json", objectMapper, new TypeReference<>() {
        });
    }
}
