package bus.station.service;

import bus.station.model.DutyAssignment;
import bus.station.repository.DutyAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyAssignmentService {
    private final DutyAssignmentRepository dutyAssignmentRepository;

    @Autowired
    public DutyAssignmentService(DutyAssignmentRepository dutyAssignmentRepository) {
        this.dutyAssignmentRepository = dutyAssignmentRepository;
    }

    public List<DutyAssignment> findAll() {
        return dutyAssignmentRepository.findAll();
    }

    public DutyAssignment save(DutyAssignment dutyAssignment) {
        if (dutyAssignment.getTripId() == null || dutyAssignment.getStaffId() == null) {
            throw new IllegalArgumentException("Trip ID and Staff ID are required.");
        }
        return dutyAssignmentRepository.save(dutyAssignment);
    }

    public Optional<DutyAssignment> findById(String id) {
        return dutyAssignmentRepository.findById(id);
    }

    public void deleteById(String id) {
        dutyAssignmentRepository.deleteById(id);
    }
}
