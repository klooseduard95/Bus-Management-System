package bus.station.service;

import bus.station.model.Ticket;
import bus.station.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepo ticketRepo;
    @Autowired
    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Optional<Ticket> findById(String id) {
        return ticketRepo.findById(id);
    }

    public List<Ticket> findAll() {
        return  ticketRepo.findAll();
    }

    public boolean deleteById(String id) {
        return ticketRepo.deleteById(id);
    }

    public void save(Ticket ticket) {
        ticketRepo.save(ticket);
    }
}
