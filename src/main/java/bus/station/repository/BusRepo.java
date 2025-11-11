package bus.station.repository;

import bus.station.model.Bus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;


@Repository
public class BusRepo extends JsonFileRepository<Bus>{

    public BusRepo(ObjectMapper objectMapper) {
        super("data/buses.json", objectMapper, new TypeReference<>() {
        });
    }
}
