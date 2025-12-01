package bus.station.controller;

import bus.station.model.Driver;
import bus.station.model.Staff;
import bus.station.model.TripManager;
import bus.station.service.DriverService;
import bus.station.service.TripManagerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("activePage", "staff");
        return "staff/index";
    }

    @GetMapping("/drivers")
    public String getDriversList(Model model){
        model.addAttribute("allStaff", driverService.findAll());
        model.addAttribute("activePage", "staff-driver");
        return "staff/driver/index";
    }

    @GetMapping("/managers")
    public String getManagersList(Model model){
        model.addAttribute("allStaff", tripManagerService.findAll());
        model.addAttribute("activePage", "staff-manager");
        return "staff/manager/index";
    }

    @GetMapping("/driver/new")
    public String addDriver(Model model){
        model.addAttribute("driver", new Driver());
        model.addAttribute("activePage", "staff");
        return "staff/driver/form";
    }

    @GetMapping("/manager/new")
    public String addManager(Model model){
        model.addAttribute("manager", new TripManager());
        model.addAttribute("activePage", "staff");
        return "staff/manager/form";
    }

    @PostMapping("/driver")
    public String createOrUpdateDriver(@Valid @ModelAttribute Driver driver, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "staff");
            return "staff/driver/form";
        }
        try {
            driverService.save(driver);
        }catch (Exception e){
            model.addAttribute("globalError", e.getMessage());
            model.addAttribute("activePage", "staff");
            return "staff/driver/form";
        }
        return "redirect:/staff/drivers";
    }

    @PostMapping("/manager")
    public String createOrUpdateManager(@Valid @ModelAttribute TripManager manager, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "staff");
            return "staff/manager/form";
        }
        try {
            tripManagerService.save(manager);
        }catch (Exception e){
            model.addAttribute("globalError", e.getMessage());
            model.addAttribute("activePage", "staff");
            return "staff/manager/form";
        }
        return "redirect:/staff/managers";
    }

    @GetMapping("/driver/{id}/edit")
    public String showEditFormDriver(@PathVariable Long id, Model model){
        Optional<Driver> driver = driverService.findById(id);

        if(driver.isPresent()){
            model.addAttribute("driver", driver.get());
            model.addAttribute("activePage", "staff");
            return "staff/driver/form";
        } else {
            return "redirect:/staff";
        }
    }

    @GetMapping("/manager/{id}/edit")
    public String showEditFormManager(@PathVariable Long id, Model model){
        Optional<TripManager> manager = tripManagerService.findById(id);

        if(manager.isPresent()){
            model.addAttribute("manager", manager.get());
            model.addAttribute("activePage", "staff");
            return "staff/manager/form";
        } else {
            return "redirect:/staff";
        }
    }

    @PostMapping("/driver/{id}/delete")
    public String deleteDriver(@PathVariable Long id){
        driverService.deleteById(id);
        return "redirect:/staff";
    }

    @PostMapping("/manager/{id}/delete")
    public String deleteTripManager(@PathVariable Long id){
        tripManagerService.deleteById(id);
        return "redirect:/staff";
    }

    @GetMapping("/{id}")
    public String getStaffDetails(@PathVariable Long id, Model model) {

        Optional<Driver> driverOpt = driverService.findById(id);
        if (driverOpt.isPresent()) {
            model.addAttribute("driver", driverOpt.get());
            model.addAttribute("activePage", "staff");
            return "staff/driver/details";
        }

        Optional<TripManager> managerOpt = tripManagerService.findById(id);
        if (managerOpt.isPresent()) {
            model.addAttribute("manager", managerOpt.get());
            model.addAttribute("activePage", "staff");
            return "staff/manager/details";
        }

        return "redirect:/staff";
    }
}