package bus.station.service;

import bus.station.model.Ticket;
import bus.station.repository.TicketRepo;

import java.util.List;
import java.util.Optional;

public class BusService {

    private final TicketRepo ticketRepo;

    public BusService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }

    public Optional<Ticket> findTicketById(String id) {
        return ticketRepo.findById(id);
    }
}
