package bus.station.controller;

import bus.station.model.TripManager;
import bus.station.service.TripManagerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/manager")
public class TripManagerController {

    private final TripManagerService tripManagerService;

    public TripManagerController(TripManagerService tripManagerService) {
        this.tripManagerService = tripManagerService;
    }

    @GetMapping
    public String getManagerList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String employeeCode,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        model.addAttribute("managers", tripManagerService.findAll(name, employeeCode, sortField, sortDir));

        model.addAttribute("name", name);
        model.addAttribute("employeeCode", employeeCode);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("activePage", "manager");
        return "manager/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("manager", new TripManager());
        model.addAttribute("activePage", "manager");
        return "manager/form";
    }

    @PostMapping
    public String createOrUpdateManager(@Valid @ModelAttribute("manager") TripManager manager,
                                        BindingResult bindingResult,
                                        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "manager");
            return "manager/form";
        }

        tripManagerService.save(manager);
        return "redirect:/manager";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<TripManager> managerOpt = tripManagerService.findById(id);

        if (managerOpt.isPresent()) {
            model.addAttribute("manager", managerOpt.get());
            model.addAttribute("activePage", "manager");
            return "manager/form";
        }
        return "redirect:/manager";
    }

    @PostMapping("/{id}/delete")
    public String deleteManager(@PathVariable Long id) {
        tripManagerService.deleteById(id);
        return "redirect:/manager";
    }

    @GetMapping("/{id}")
    public String getManagerDetails(@PathVariable Long id, Model model) {
        Optional<TripManager> managerOpt = tripManagerService.findById(id);

        if (managerOpt.isPresent()) {
            model.addAttribute("manager", managerOpt.get());
            model.addAttribute("activePage", "manager");
            return "manager/details";
        }
        return "redirect:/manager";
    }
}