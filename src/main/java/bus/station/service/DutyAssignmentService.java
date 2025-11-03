package bus.station.service;

import bus.station.model.DutyAssignment;
import bus.station.repository.DutyAssignmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyAssignmentService {
    private final DutyAssignmentRepo dutyAssignmentRepo;

    @Autowired
    public DutyAssignmentService(DutyAssignmentRepo dutyAssignmentRepo) {
        this.dutyAssignmentRepo = dutyAssignmentRepo;
    }

    public List<DutyAssignment> findAll() {
        return dutyAssignmentRepo.findAll();
    }

    public DutyAssignment save(DutyAssignment dutyAssignment) {
        if (dutyAssignment.getTripId() == null || dutyAssignment.getStaffId() == null) {
            throw new IllegalArgumentException("Trip ID and Staff ID are required.");
        }
        return dutyAssignmentRepo.save(dutyAssignment);
    }

    public Optional<DutyAssignment> findById(String id) {
        return dutyAssignmentRepo.findById(id);
    }

    public void deleteById(String id) {
        dutyAssignmentRepo.deleteById(id);
    }
}
