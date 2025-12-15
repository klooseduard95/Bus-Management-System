package bus.station.controller;

import bus.station.enums.Role;
import bus.station.model.DutyAssignment;
import bus.station.service.BusTripService;
import bus.station.service.DriverService; // 1. Folosim DriverService
import bus.station.service.DutyAssignmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/duty-assignment")
public class DutyAssignmentController {

    private final DutyAssignmentService dutyAssignmentService;
    private final BusTripService busTripService;
    private final DriverService driverService;

    public DutyAssignmentController(DutyAssignmentService dutyAssignmentService,
                                    BusTripService busTripService,
                                    DriverService driverService) {
        this.dutyAssignmentService = dutyAssignmentService;
        this.busTripService = busTripService;
        this.driverService = driverService;
    }

    private void addDropdownDataToModel(Model model) {
        model.addAttribute("allTrips", busTripService.findAll());
        model.addAttribute("allDrivers", driverService.findAll());
    }

    @GetMapping
    public String getAssignmentList(
            @RequestParam(required = false) Long tripId,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) String driverName,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        model.addAttribute("assignments", dutyAssignmentService.findAll(tripId, role, driverName, sortField, sortDir));

        model.addAttribute("tripId", tripId);
        model.addAttribute("role", role);
        model.addAttribute("driverName", driverName);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("activePage", "duty-assignment");
        return "duty-assignment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new DutyAssignment());
        addDropdownDataToModel(model);
        model.addAttribute("activePage", "duty-assignment");
        return "duty-assignment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<DutyAssignment> assignmentOptional = dutyAssignmentService.findById(id);
        if (assignmentOptional.isPresent()) {
            model.addAttribute("assignment", assignmentOptional.get());
            addDropdownDataToModel(model);
            model.addAttribute("activePage", "duty-assignment");
            return "duty-assignment/form";
        } else {
            return "redirect:/duty-assignment";
        }
    }

    @PostMapping
    public String createOrUpdateAssignment(@Valid @ModelAttribute("assignment") DutyAssignment assignment,
                                           BindingResult bindingResult,
                                           Model model) {
        if (bindingResult.hasErrors()) {
            addDropdownDataToModel(model);
            model.addAttribute("activePage", "duty-assignment");
            return "duty-assignment/form";
        }

        try {
            dutyAssignmentService.save(assignment);
        } catch (Exception e) {
            model.addAttribute("globalError", e.getMessage());

            addDropdownDataToModel(model);
            model.addAttribute("activePage", "duty-assignment");

            return "duty-assignment/form";
        }
        return "redirect:/duty-assignment";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable Long id) {
        dutyAssignmentService.deleteById(id);
        return "redirect:/duty-assignment";
    }

    @GetMapping("/{id}")
    public String getAssignmentDetails(@PathVariable Long id, Model model) {
        Optional<DutyAssignment> assignmentOpt = dutyAssignmentService.findById(id);

        if (assignmentOpt.isPresent()) {
            model.addAttribute("assignment", assignmentOpt.get());
            model.addAttribute("activePage", "duty-assignment");
            return "duty-assignment/details";
        }
        return "redirect:/duty-assignment";
    }
}