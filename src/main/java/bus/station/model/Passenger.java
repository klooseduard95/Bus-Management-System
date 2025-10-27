package bus.station.model;

import java.util.List;

public class Passenger {
    private String id;
    private String name;
    private String currency;
    private List<Ticket> tickets;
    private String email;
    private String loyaltyLevel;

    public Passenger(String id, String name, String currency, List<Ticket> tickets, String email, String loyaltyLevel) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.tickets = tickets;
        this.email = email;
        this.loyaltyLevel = loyaltyLevel;
    }

    public String getId() {
        return id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoyaltyLevel() {
        return loyaltyLevel;
    }

    public void setLoyaltyLevel(String loyaltyLevel) {
        this.loyaltyLevel = loyaltyLevel;
    }
}
