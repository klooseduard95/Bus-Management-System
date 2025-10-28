package bus.station.repository;

import bus.station.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepo extends InMemoryRepo<Ticket>{
}
