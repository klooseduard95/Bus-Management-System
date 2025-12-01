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
        if (dutyAssignment.getBusTrip() == null) {
            throw new IllegalArgumentException("Bus Trip is required.");
        }

        if (dutyAssignment.getDriver() == null && dutyAssignment.getManager() == null) {
            throw new IllegalArgumentException("An assignment must have either a Driver or a Trip Manager.");
        }

        if (dutyAssignment.getDriver() != null && dutyAssignment.getManager() != null) {
            throw new IllegalArgumentException("Cannot assign both a Driver and a Manager in the same assignment entry.");
        }

        return dutyAssignmentRepository.save(dutyAssignment);
    }

    public Optional<DutyAssignment> findById(Long id) {
        return dutyAssignmentRepository.findById(id);
    }

    public void deleteById(Long id) {
        dutyAssignmentRepository.deleteById(id);
    }
}