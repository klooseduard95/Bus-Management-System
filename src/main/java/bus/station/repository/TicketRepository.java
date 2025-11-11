package bus.station.repository;

import bus.station.model.BusStation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository extends JsonFileRepository<BusStation> {

    public TicketRepository(ObjectMapper objectMapper) {
        super("data/ticket", objectMapper, new TypeReference<>() {});
    }
}
