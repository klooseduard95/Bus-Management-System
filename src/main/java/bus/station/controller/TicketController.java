package bus.station.controller;

import bus.station.model.Ticket;
import bus.station.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String listAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        return "/ticket/index";
    }

    @GetMapping("/new")
    public String newTicket(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "/ticket/form";
    }

    @PostMapping
    public String createOrUpdateTicket(@ModelAttribute Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteByID(id);
        return "redirect:/tickets";
    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Ticket> ticket = ticketService.findById(id);

        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "ticket/form";
        } else {
            return "redirect:/tickets";
        }
    }
}
