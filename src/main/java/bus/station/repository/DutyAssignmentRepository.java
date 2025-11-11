package bus.station.repository;

import bus.station.model.BusStation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DutyAssignmentRepository extends JsonFileRepository<BusStation> {

    public DutyAssignmentRepository(ObjectMapper objectMapper) {
        super("data/duty-assignment", objectMapper, new TypeReference<>() {});
    }
}
