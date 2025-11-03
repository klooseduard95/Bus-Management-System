package bus.station.controller;

import bus.station.model.Ticket;
import bus.station.repository.BusTripRepo;
import bus.station.repository.PassengerRepo;
import bus.station.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final BusTripRepo busTripRepo;
    private final PassengerRepo passengerRepo;

    public TicketController(TicketService ticketService, BusTripRepo busTripRepo, PassengerRepo passengerRepo) {
        this.ticketService = ticketService;
        this.busTripRepo = busTripRepo;
        this.passengerRepo = passengerRepo;
    }

    private void addDropdownDataToModel(Model model) {
        model.addAttribute("allTrips", busTripRepo.findAll());
        model.addAttribute("allPassengers", passengerRepo.findAll());
    }

    @GetMapping
    public String getTicketList(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        model.addAttribute("activePage", "ticket");
        return "ticket/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        addDropdownDataToModel(model);
        model.addAttribute("activePage", "ticket");
        return "ticket/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Ticket> ticketOptional = ticketService.findById(id);
        if (ticketOptional.isPresent()) {
            model.addAttribute("ticket", ticketOptional.get());
            addDropdownDataToModel(model);
            model.addAttribute("activePage", "ticket");
            return "ticket/form";
        } else {
            return "redirect:/ticket";
        }
    }


    @PostMapping
    public String createOrUpdateTicket(@ModelAttribute Ticket ticket) {
        try {
            ticketService.save(ticket);
        } catch (Exception e) {
            System.err.println("Eroare la salvarea biletului: " + e.getMessage());
        }
        return "redirect:/ticket";
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteById(id);
        return "redirect:/ticket";
    }
}
