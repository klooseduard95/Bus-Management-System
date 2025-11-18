package bus.station.model;

import bus.station.interfaces.Identifiable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Trip is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_trip_id")
    private BusTrip busTrip;

    @NotNull(message = "Passenger is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_trip_id")
    private Passenger passenger;

    @NotBlank(message = "Seat number is required")
    @Size(min = 1, max=5, message = "Seat number must be between 1 and 5 characters")
    private String seatNumber;

    @Positive(message = "Price must be positive")
    private double price;

    public Ticket() {}

    public Ticket(BusTrip busTrip, Passenger passenger, String seatNumber, double price) {
        this.busTrip = busTrip;
        this.passenger = passenger;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusTrip getBusTrip() {
        return busTrip;
    }

    public void setBusTrip(BusTrip busTrip) {
        this.busTrip = busTrip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
