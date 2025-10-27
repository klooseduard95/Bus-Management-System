package bus.station.repository;

import bus.station.model.Passenger;

import java.util.List;
import java.util.Optional;

public class PassengerRepo extends InMemoryRepo<Passenger> {
    @Override
    public Passenger save(Passenger entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<Passenger> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<Passenger> findAll() {
        return super.findAll();
    }
}
