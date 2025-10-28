package bus.station.controller;

import bus.station.model.Bus;
import bus.station.service.BusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buses")
public class BusController {
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }
    @GetMapping("/{id}")
    public Optional<Bus> findBusById(String id) {
        return busService.findBusById(id);
    }

    @GetMapping("/")
    public List<Bus> findAll() {
        return busService.findAll();
    }

}
