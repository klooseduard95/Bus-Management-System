package bus.station.controller;

import bus.station.model.Passenger;
import bus.station.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping("/{id}")
    public Optional<Passenger> findById(@PathVariable String id) {
        return passengerService.findPassengerById(id);
    }

    @RequestMapping("/")
    public List<Passenger> findAll() {
        return passengerService.findAllPassenger();
    }
}
