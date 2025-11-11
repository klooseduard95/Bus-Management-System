package bus.station.repository;

import bus.station.model.Bus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;


@Repository
public class BusRepository extends JsonFileRepository<Bus>{

    public BusRepository(ObjectMapper objectMapper) {
        super("data/buses.json", objectMapper, new TypeReference<>() {
        });
    }
}
