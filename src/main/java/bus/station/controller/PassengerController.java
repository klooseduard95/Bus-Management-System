package bus.station.controller;

import bus.station.model.Bus;
import bus.station.model.Passenger;
import bus.station.service.PassengerService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
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
    public String getPassengerList(Model model) {
        model.addAttribute("passengers",passengerService.findAllPassenger());
        return "passenger/index";
    }

    @GetMapping("/new")
    public String addPassenger(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passenger/form";
    }

    @PostMapping
    public String createOrUpdatePassenger(@ModelAttribute Passenger passenger) {
        passengerService.save(passenger);
        return "redirect:/passenger";
    }

    @PostMapping("{id}/delete")
    public String deleteBus(@PathVariable String id) {
        passengerService.deleteById(id);

        return "redirect:/passenger";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Passenger> passengerOptional = passengerService.findPassengerById(id);

        if (passengerOptional.isPresent()) {
            model.addAttribute("passenger", passengerOptional.get());
            return "passenger/form";
        } else {
            return "redirect:/passenger";
        }
    }
}
