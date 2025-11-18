package bus.station.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Passenger name is required")
    @Size(min = 3, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    private String currency;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth mus be in the past")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "requires_special_assistance")
    private boolean requiresSpecialAssistance;

    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Passenger() {}

    public Passenger(String name, String currency, LocalDate dateOfBirth, boolean requiresSpecialAssistance) {
        this.name = name;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
        this.requiresSpecialAssistance = requiresSpecialAssistance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
