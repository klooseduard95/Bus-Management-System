package bus.station.repository;

import bus.station.model.Staff;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffRepository extends JsonFileRepository<Staff>{

    public StaffRepository(ObjectMapper objectMapper) {
        super("data/staff.json", objectMapper, new TypeReference<List<Staff>>() {
        });
    }
}
