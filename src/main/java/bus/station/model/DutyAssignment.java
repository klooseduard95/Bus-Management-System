package bus.station.model;

import bus.station.enums.Role;

public class DutyAssignment {
    private String Id;
    private String TripId;
    private String StaffId;
    private Role role;

    public DutyAssignment(String id, String tripId, String staffId, Role role) {
        Id = id;
        TripId = tripId;
        StaffId = staffId;
        this.role = role;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTripId() {
        return TripId;
    }

    public void setTripId(String tripId) {
        TripId = tripId;
    }

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
