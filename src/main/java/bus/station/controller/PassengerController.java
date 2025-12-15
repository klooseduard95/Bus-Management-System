package bus.station.controller;

import bus.station.model.Bus;
import bus.station.model.Passenger;
import bus.station.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @GetMapping
    public String getPassengerList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean specialAssistance,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        model.addAttribute("passengers", passengerService.findAll(name, specialAssistance, sortField, sortDir));

        model.addAttribute("name", name);
        model.addAttribute("specialAssistance", specialAssistance);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("activePage", "passenger");
        return "passenger/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        model.addAttribute("activePage", "passenger");
        return "passenger/form";
    }

    @PostMapping
    public String createOrUpdatePassenger(@Valid @ModelAttribute Passenger passenger, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "passenger");
            return "passenger/form";
        }
        try {
            passengerService.save(passenger);
        }catch(Exception e) {
            model.addAttribute("globalError", e.getMessage());
            model.addAttribute("activePage", "passenger");
            return "redirect:/passenger";
        }
        return "redirect:/passenger";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Passenger> passengerOptional = passengerService.findById(id);

        if (passengerOptional.isPresent()) {
            model.addAttribute("passenger", passengerOptional.get());
            model.addAttribute("activePage", "passenger");
            return "passenger/form";
        } else {
            return "redirect:/passenger";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePassenger(@PathVariable Long id) {
        passengerService.deleteById(id);
        return "redirect:/passenger";
    }

    @GetMapping("/{id}")
    public String getPassengerDetails(@PathVariable Long id, Model model) {
        Optional<Passenger> passengerOpt = passengerService.findById(id);

        if (passengerOpt.isPresent()) {
            model.addAttribute("passenger", passengerOpt.get());
            model.addAttribute("activePage", "passenger");
            return "passenger/details";
        }
        return "redirect:/passenger";
    }
}
