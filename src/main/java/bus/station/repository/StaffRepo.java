package bus.station.repository;

import bus.station.model.Staff;

import java.util.List;
import java.util.Optional;

public class StaffRepo extends InMemoryRepo<Staff>{
    @Override
    public Staff save(Staff entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<Staff> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<Staff> findAll() {
        return super.findAll();
    }
}
