package bus.station.model;

import bus.station.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "duty_assignments")
public class       DutyAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_trip_id")
    @NotNull(message = "Trips is required")
    private BusTrip busTrip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    @NotNull(message = "Staff member is required")
    private Staff staff;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Role role;

    public DutyAssignment() {}

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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
