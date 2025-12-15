package bus.station.controller;

import bus.station.model.BusTrip;
import bus.station.service.BusTripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final BusTripService busTripService;

    public HomeController(BusTripService busTripService) {
        this.busTripService = busTripService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<BusTrip> activeTrips = busTripService.findActiveTrips();
        model.addAttribute("activeTrips", activeTrips);
        model.addAttribute("activePage", "home");
        return "index";
    }
}