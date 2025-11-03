package bus.station.controller;

import bus.station.model.Ticket;
import bus.station.service.TicketService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping("/{id}")
    public Optional<Ticket> findById(@PathVariable String id) {
        return ticketService.findById(id);
    }

    @RequestMapping("/")
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
}
