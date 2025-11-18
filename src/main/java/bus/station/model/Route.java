package bus.station.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Origin station is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_station_id")
    private BusStation origin;

    @NotNull(message = "Destination station is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_station_id")
    private BusStation destination;

    @NotNull(message = "Distance is required")
    @Positive(message = "Distance must be a positive number")
    private double distance;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private List<BusTrip> trips;

    public Route() {}

    public Route(BusStation origin, BusStation destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusStation getOrigin() {
        return origin;
    }

    public void setOrigin(BusStation origin) {
        this.origin = origin;
    }

    public BusStation getDestination() {
        return destination;
    }

    public void setDestination(BusStation destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<BusTrip> getTrips() {
        return trips;
    }

    public void setTrips(List<BusTrip> trips) {
        this.trips = trips;
    }
}
