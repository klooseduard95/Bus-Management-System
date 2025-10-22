package bus.station.model;

import java.util.List;

public class Route {
    private String id;
    private BusStation origin;
    private BusStation destination;
    private double distance;
    private List<BusTrip> trips;
}
