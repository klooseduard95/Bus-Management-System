package bus.station.repository;

import bus.station.model.Ticket;

import java.util.List;
import java.util.Optional;

public class TicketRepo extends InMemoryRepo<Ticket>{
    @Override
    public Ticket save(Ticket entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<Ticket> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return super.findAll();
    }
}
