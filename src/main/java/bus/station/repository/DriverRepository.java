package bus.station.repository;

import bus.station.model.Driver;
import bus.station.model.Staff;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverRepository extends JsonFileRepository<Driver> {
    public DriverRepository(ObjectMapper objectMapper) {
        super("data/drivers.json", objectMapper, new TypeReference<>() {
        });
    }

}
