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

    public Optional<DutyAssignment> findDutyAssignmentById(String id) {
        return dutyAssignmentRepo.findById(id);
    }
}
