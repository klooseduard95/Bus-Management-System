package bus.station.controller;

import bus.station.model.Ticket;
import bus.station.service.BusTripService; // Folosim Service
import bus.station.service.PassengerService; // Folosim Service
import bus.station.service.TicketService;
import jakarta.validation.Valid; // Validare
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Gestionare Erori
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final BusTripService busTripService;
    private final PassengerService passengerService;

    public TicketController(TicketService ticketService,
                            BusTripService busTripService,
                            PassengerService passengerService) {
        this.ticketService = ticketService;
        this.busTripService = busTripService;
        this.passengerService = passengerService;
    }

    private void addDropdownDataToModel(Model model) {
        model.addAttribute("allTrips", busTripService.findAll());
        model.addAttribute("allPassengers", passengerService.findAll());
    }

    @GetMapping
    public String getTicketList(
            @RequestParam(required = false) Long tripId,
            @RequestParam(required = false) String passengerName,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        model.addAttribute("tickets", ticketService.findAll(tripId, passengerName, maxPrice, sortField, sortDir));

        model.addAttribute("tripId", tripId);
        model.addAttribute("passengerName", passengerName);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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
    public String showEditForm(@PathVariable Long id, Model model) {
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
    public String createOrUpdateTicket(@Valid @ModelAttribute Ticket ticket, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            addDropdownDataToModel(model);
            model.addAttribute("activePage", "ticket");
            return "ticket/form";
        }

        try {
            ticketService.save(ticket);

        } catch (RuntimeException e) {
            model.addAttribute("globalError", e.getMessage());
            addDropdownDataToModel(model);
            model.addAttribute("activePage", "ticket");
            return "ticket/form";
        }

        return "redirect:/ticket";
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
        return "redirect:/ticket";
    }

    @GetMapping("/{id}")
    public String getTicketDetails(@PathVariable Long id, Model model) {
        Optional<Ticket> ticketOpt = ticketService.findById(id);

        if (ticketOpt.isPresent()) {
            model.addAttribute("ticket", ticketOpt.get());
            model.addAttribute("activePage", "ticket");
            return "ticket/details";
        }
        return "redirect:/ticket";
    }
}