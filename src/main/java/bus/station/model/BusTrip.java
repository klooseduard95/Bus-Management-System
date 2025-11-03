package bus.station.model;

import bus.station.enums.BusTripStatus;
import bus.station.interfaces.Identifiable;

import java.time.LocalTime;
import java.util.List;


public class BusTrip implements Identifiable {
    private String id;
    private String routeId;
    private String busId;
    private LocalTime startTime;
    private List<Ticket> tickets;
    private List<DutyAssignment> assignments;
    private BusTripStatus busTripStatus;
    private int availableSeats;
    private double basePrice;

    public BusTrip() {}

    public BusTrip(String id, String routeId, String busId, LocalTime startTime, List<Ticket> tickets, List<DutyAssignment> assignments, BusTripStatus busTripStatus, int availableSeats, double basePrice) {
        this.id = id;
        this.routeId = routeId;
        this.busId = busId;
        this.startTime = startTime;
        this.tickets = tickets;
        this.assignments = assignments;
        this.busTripStatus = busTripStatus;
        this.availableSeats = availableSeats;
        this.basePrice = basePrice;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
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
        return busTripStatus;
    }

    public void setStatus(BusTripStatus busTripStatus) {
        this.busTripStatus = busTripStatus;
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
