package bus.station.repository;

import bus.station.model.Route;

import java.util.List;
import java.util.Optional;

public class RouteRepo extends InMemoryRepo<Route>{
    @Override
    public Route save(Route entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<Route> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<Route> findAll() {
        return super.findAll();
    }
}
