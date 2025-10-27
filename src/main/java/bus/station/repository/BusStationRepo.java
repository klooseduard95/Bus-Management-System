package bus.station.repository;

import bus.station.model.BusStation;

import java.util.List;
import java.util.Optional;

public class BusStationRepo extends InMemoryRepo<BusStation> {
    @Override
    public BusStation save(BusStation entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<BusStation> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<BusStation> findAll() {
        return super.findAll();
    }
}
