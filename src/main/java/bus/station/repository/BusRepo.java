package bus.station.repository;

import bus.station.interfaces.Identifiable;
import bus.station.model.Bus;

import java.util.List;
import java.util.Optional;

public class BusRepo extends InMemoryRepo<Bus>{
    @Override
    public Bus save(Bus entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<Bus> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<Bus> findAll() {
        return super.findAll();
    }
}
