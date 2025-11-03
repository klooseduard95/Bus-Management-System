package bus.station.controller;


import bus.station.model.DutyAssignment;
import bus.station.service.DutyAssignmentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dutyassignments")
public class DutyAssignmentController {
    private final DutyAssignmentService dutyAssignmentService;

    public DutyAssignmentController(DutyAssignmentService dutyAssignmentService) {
        this.dutyAssignmentService = dutyAssignmentService;
    }

    @RequestMapping("/")
    public List<DutyAssignment> findAll() {
        return dutyAssignmentService.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<DutyAssignment> findDutyAssignmentById(@PathVariable String id) {
        return dutyAssignmentService.findDutyAssignmentById(id);
    }
}
