package bus.station.service;

import bus.station.model.Ticket;
import bus.station.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> findById(String id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> findAll() {
        return  ticketRepository.findAll();
    }

    public boolean deleteById(String id) {
        return ticketRepository.deleteById(id);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
