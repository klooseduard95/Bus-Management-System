package bus.station.repository;

import bus.station.model.TripManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TripManagerRepository extends JsonFileRepository<TripManager> {
    public TripManagerRepository(ObjectMapper objectMapper) {
        super("data/driver.json", objectMapper, new TypeReference<>() {
        });
    }
}
