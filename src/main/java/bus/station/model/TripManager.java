package bus.station.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "trip_managers")
public class TripManager extends Staff {

    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DutyAssignment> assignments;

    public TripManager() {}

    public TripManager(String name, String employeeCode) {
        super(name);
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }
    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }
}
