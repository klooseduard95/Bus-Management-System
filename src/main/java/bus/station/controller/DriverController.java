package bus.station.controller;

import bus.station.model.Driver;
import bus.station.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("activePage", "driver");
        return "driver/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("activePage", "driver");
        return "driver/form";
    }

    @PostMapping
    public String createOrUpdate(@Valid @ModelAttribute Driver driver, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("activePage", "driver");
            return "driver/form";
        }
        driverService.save(driver);
        return "redirect:/driver";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Driver> driverOpt = driverService.findById(id);
        if (driverOpt.isPresent()) {
            model.addAttribute("driver", driverOpt.get());
            model.addAttribute("activePage", "driver");
            return "driver/form";
        }
        return "redirect:/driver";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        driverService.deleteById(id);
        return "redirect:/driver";
    }

    @GetMapping("/{id}")
    public String getDetails(@PathVariable Long id, Model model) {
        Optional<Driver> driverOpt = driverService.findById(id);
        if (driverOpt.isPresent()) {
            model.addAttribute("driver", driverOpt.get());
            model.addAttribute("activePage", "driver");
            return "driver/details";
        }
        return "redirect:/driver";
    }
}