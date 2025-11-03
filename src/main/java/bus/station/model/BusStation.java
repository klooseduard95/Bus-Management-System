package bus.station.model;

import bus.station.interfaces.Identifiable;

import java.util.List;

public class BusStation implements Identifiable {
    private String id;
    private String name;
    private String city;
    private List<BusTrip> trips;
    private String openingHours;
    private boolean parkingAvailable;

    public BusStation() {}

    public BusStation(String id, String name, String city, List<BusTrip> trips, String openingHours, boolean parkingAvailable) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.trips = trips;
        this.openingHours = openingHours;
        this.parkingAvailable = parkingAvailable;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<BusTrip> getTrips() {
        return trips;
    }

    public void setTrips(List<BusTrip> trips) {
        this.trips = trips;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public boolean isParkingAvailable() {
        return parkingAvailable;
    }

    public void setParkingAvailable(boolean parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }
}
