package bus.station.model;

import bus.station.interfaces.Identifiable;

import java.util.List;

public class Route implements Identifiable {
    private String id;
    private BusStation origin;
    private BusStation destination;
    private double distance;
    private List<BusTrip> trips;

    public Route() {}

    public Route(String id, BusStation origin, BusStation destination, double distance, List<BusTrip> trips) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.trips = trips;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
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
