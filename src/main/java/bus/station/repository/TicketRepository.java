package bus.station.repository;

import bus.station.model.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository extends JsonFileRepository<Ticket> {

    public TicketRepository(ObjectMapper objectMapper) {
        super("data/tickets.json", objectMapper, new TypeReference<>() {});
    }
}
