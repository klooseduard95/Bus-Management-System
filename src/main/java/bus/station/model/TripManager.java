package bus.station.model;

import java.util.List;

public class TripManager extends Staff{
    private List<DutyAssignment> assignments;
    private String employeeCode;

    public TripManager() {}

    public TripManager(String id, String name, List<DutyAssignment> assignments, String employeeCode) {
        super(id, name);
        this.employeeCode = employeeCode;
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
