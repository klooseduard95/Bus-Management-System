package bus.station.model;

import java.util.List;

public class Driver extends Staff {
    private List<DutyAssignment> assignments;
    private int yearsOfExperience;

    public Driver() {}

    public Driver(String id, String name, List<DutyAssignment> assignments, int yearsOfExperience) {
        super(id, name);
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
