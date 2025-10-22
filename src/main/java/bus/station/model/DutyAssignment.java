package bus.station.model;

public class DutyAssignment {
    private String Id;
    private String TripId;
    private String StaffId;
    private enum Role{PrimaryDriver, ReservDriver}
    private Role role;
}
