package bus.station.controller;

import bus.station.model.Driver;
import bus.station.model.Passenger;
import bus.station.model.Staff;
import bus.station.model.TripManager;
import bus.station.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public String getAllStaff(Model model){
        model.addAttribute("allStaff", staffService.findAll());
        model.addAttribute("staffType", Staff.class.getSimpleName());
        return "staff/index";
    }

    @GetMapping("/driver/new")
    public String addDriver(Model model){
        model.addAttribute("driver", new Driver());
        return "staff/driver/form";
    }

    @GetMapping("/manager/new")
    public String addManager(Model model){
        model.addAttribute("manager", new TripManager());
        return "staff/manager/form";
    }

    @PostMapping("/driver")
    public String createOrUpdateDriver(@ModelAttribute Driver driver) {
        staffService.save(driver);
        return "redirect:/staff";
    }

    @PostMapping("/manager")
    public String createOrUpdateManager(@ModelAttribute TripManager manager) {
        staffService.save(manager);
        return "redirect:/staff";
    }

    @GetMapping("/{id}/driver/edit")
    public String showEditFormDriver(@PathVariable String id, Model model){
        Optional<Staff> driver = staffService.findById(id);

        if(driver.isPresent() && driver.get() instanceof Driver){
            model.addAttribute("driver", (Driver) driver.get());
            return "staff/driver/form";
        } else {
            return "redirect:/staff";
        }
    }

    @GetMapping("/{id}/manager/edit")
    public String showEditFormManager(@PathVariable String id, Model model){
        Optional<Staff> manager = staffService.findById(id);

        if(manager.isPresent() && manager.get() instanceof TripManager){
            model.addAttribute("manager", (TripManager) manager.get());
            return "staff/manager/form";
        } else {
            return "redirect:/staff";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id){
        staffService.delete(id);
        return "redirect:/staff";
    }
}
