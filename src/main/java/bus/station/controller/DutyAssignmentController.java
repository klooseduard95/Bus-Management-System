package bus.station.controller;


import bus.station.model.DutyAssignment;
import bus.station.repository.BusTripRepository;
import bus.station.repository.StaffRepo;
import bus.station.service.DutyAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/duty-assignment")
public class DutyAssignmentController {

    private final DutyAssignmentService dutyAssignmentService;
    private final BusTripRepository busTripRepository;
    private final StaffRepo staffRepo;

    public DutyAssignmentController(DutyAssignmentService dutyAssignmentService,
                                    BusTripRepository busTripRepository,
                                    StaffRepo staffRepo) {
        this.dutyAssignmentService = dutyAssignmentService;
        this.busTripRepository = busTripRepository;
        this.staffRepo = staffRepo;
    }

    private void addDropdownDataToModel(Model model) {
        model.addAttribute("allTrips", busTripRepository.findAll());

        model.addAttribute("allStaff", staffRepo.findAll());
    }

    @GetMapping
    public String getAssignmentList(Model model) {
        model.addAttribute("assignments", dutyAssignmentService.findAll());
        return "duty-assignment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new DutyAssignment());
        addDropdownDataToModel(model);
        return "duty-assignment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<DutyAssignment> assignmentOptional = dutyAssignmentService.findById(id);
        if (assignmentOptional.isPresent()) {
            model.addAttribute("assignment", assignmentOptional.get());
            addDropdownDataToModel(model);
            return "duty-assignment/form";
        } else {
            return "redirect:/duty-assignment";
        }
    }

    @PostMapping
    public String createOrUpdateAssignment(@ModelAttribute DutyAssignment assignment) {
        dutyAssignmentService.save(assignment);
        return "redirect:/duty-assignment";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable String id) {
        dutyAssignmentService.deleteById(id);
        return "redirect:/duty-assignment";
    }
}
