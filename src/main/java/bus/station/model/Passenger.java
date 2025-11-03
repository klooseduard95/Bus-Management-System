package bus.station.model;

import bus.station.interfaces.Identifiable;

import java.time.LocalDate;
import java.util.List;

public class Passenger implements Identifiable {
    private String id;
    private String name;
    private String currency;
    private List<Ticket> tickets;
    private LocalDate dateOfBirth;
    private boolean requiresSpecialAssistance;

    public Passenger(String id, String name, String currency, List<Ticket> tickets, String email, String loyaltyLevel, LocalDate dateOfBirth, boolean requiresSpecialAssistance) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.tickets = tickets;
        this.dateOfBirth = dateOfBirth;
        this.requiresSpecialAssistance = requiresSpecialAssistance;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isRequiresSpecialAssistance() {
        return requiresSpecialAssistance;
    }

    public void setRequiresSpecialAssistance(boolean requiresSpecialAssistance) {
        this.requiresSpecialAssistance = requiresSpecialAssistance;
    }
}
