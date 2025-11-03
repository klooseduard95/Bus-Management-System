package bus.station.controller;


import bus.station.model.BusStation;
import bus.station.service.BusStationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/busstations")
public class BusStationController {
    private final BusStationService busStationService;

    public BusStationController(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @RequestMapping("/")
    public List<BusStation> findAll() {
        return busStationService.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<BusStation> findBusStationById(@PathVariable String id) {
        return busStationService.findBusStationById(id);
    }
}
