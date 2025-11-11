package bus.station.controller;

import bus.station.model.Driver;
import bus.station.model.Staff;
import bus.station.model.TripManager;
import bus.station.service.DriverService;
import bus.station.service.TripManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final DriverService driverService;
    private final TripManagerService tripManagerService;
    public StaffController(DriverService driverService, TripManagerService tripManagerService) {
        this.driverService = driverService;
        this.tripManagerService = tripManagerService;
    }

    @GetMapping
    public String getAllStaff(Model model){
        List<Driver> drivers = driverService.findAll();
        List<TripManager> managers = tripManagerService.findAll();

        List<Staff> allStaff = new ArrayList<>();
        allStaff.addAll(drivers);
        allStaff.addAll(managers);

        model.addAttribute("allStaff", allStaff);
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
        driverService.saveDriver(driver);
        return "redirect:/staff";
    }

    @PostMapping("/manager")
    public String createOrUpdateManager(@ModelAttribute TripManager manager) {
        tripManagerService.save(manager);
        return "redirect:/staff";
    }

    @GetMapping("/driver/{id}/edit")
    public String showEditFormDriver(@PathVariable String id, Model model){
        Optional<Driver> driver = driverService.findDriverById(id);

        if(driver.isPresent()){
            model.addAttribute("driver", (Driver) driver.get());
            return "staff/driver/form";
        } else {
            return "redirect:/staff";
        }
    }

    @GetMapping("/manager/{id}/edit")
    public String showEditFormManager(@PathVariable String id, Model model){
        Optional<TripManager> manager = tripManagerService.findManagerById(id);

        if(manager.isPresent()){
            model.addAttribute("manager", (TripManager) manager.get());
            return "staff/manager/form";
        } else {
            return "redirect:/staff";
        }
    }

    @PostMapping("/driver/{id}/delete")
    public String deleteDriver(@PathVariable String id){
        driverService.deleteDriverById(id);
        return "redirect:/staff";
    }

    @PostMapping("/manager/{id}/delete")
    public String deleteTripManager(@PathVariable String id){
        tripManagerService.delete(id);
        return "redirect:/staff";
    }

}
