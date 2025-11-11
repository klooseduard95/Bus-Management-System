package bus.station.repository;

import bus.station.model.DutyAssignment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DutyAssignmentRepository extends JsonFileRepository<DutyAssignment> {

    public DutyAssignmentRepository(ObjectMapper objectMapper) {
        super("data/duty-assignment", objectMapper, new TypeReference<>() {});
    }
}
