package bus.station.model;

import java.time.LocalTime;
import java.util.List;


public class BusTrip {
    private String id;
    private String routeId;
    private String busId;
    private LocalTime startTime;
    private List<Ticket> tickets;
    private List<DutyAssignment> assignments;
    private enum Status {Planned, Active, Completed};
    private Status status;
}
