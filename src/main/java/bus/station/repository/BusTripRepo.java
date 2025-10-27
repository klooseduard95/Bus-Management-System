package bus.station.repository;

import bus.station.model.BusTrip;

import java.util.List;
import java.util.Optional;

public class BusTripRepo extends InMemoryRepo<BusTrip>{
    @Override
    public BusTrip save(BusTrip entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<BusTrip> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<BusTrip> findAll() {
        return super.findAll();
    }
}
