package bus.station.repository;

import bus.station.model.DutyAssignment;

import java.util.List;
import java.util.Optional;

public class DutyAssignmentRepo extends InMemoryRepo<DutyAssignment> {
    @Override
    public DutyAssignment save(DutyAssignment entity) {
        return super.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        return super.deleteById(id);
    }

    @Override
    public Optional<DutyAssignment> findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<DutyAssignment> findAll() {
        return super.findAll();
    }
}
