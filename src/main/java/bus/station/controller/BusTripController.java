package bus.station.controller;

import bus.station.model.BusTrip;
import bus.station.service.BusTripService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bustrips")
public class BusTripController {
    private final BusTripService busTripService;

    public BusTripController(BusTripService busTripService) {
        this.busTripService = busTripService;
    }

    @RequestMapping("/")
    public List<BusTrip> findAll() {
        return busTripService.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<BusTrip> findBusTripById(@PathVariable String id) {
        return busTripService.findBusTripById(id);
    }
}
