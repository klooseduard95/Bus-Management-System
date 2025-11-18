package bus.station.model;

import bus.station.enums.BusTripStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "bus_trips")
public class BusTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @Enumerated(EnumType.STRING)
    private BusTripStatus status;

    @NotNull(message = "Available seats is required")
    @Min(value = 0, message = "Available seats cannot pe negative")
    private int availableSeats;

    @NotNull(message = "Base price is required")
    @Positive(message = "Base price must be positive")
    private double basePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    @NotNull(message = "Route is required")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    @NotNull(message = "Bus is required")
    private Bus bus;

    @OneToMany(mappedBy = "busTrip")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "busTrip")
    private List<DutyAssignment> assignments;

    public BusTrip() {
        this.status = BusTripStatus.Planned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }

    public BusTripStatus getStatus() {
        return status;
    }

    public void setStatus(BusTripStatus status) {
        this.status = status;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
