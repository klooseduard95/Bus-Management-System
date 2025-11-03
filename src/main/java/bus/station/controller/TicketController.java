package bus.station.controller;

import bus.station.model.Ticket;
import bus.station.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public Optional<Ticket> findById(@PathVariable String id) {
        return ticketService.findById(id);
    }

    @GetMapping("/")
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
}
