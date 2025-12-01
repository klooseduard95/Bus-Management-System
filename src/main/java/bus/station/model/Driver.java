package bus.station.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver extends Staff {
    @NotNull(message = "License acquired date is missing")
    @PastOrPresent(message = "License date must be in the past")
    private LocalDate licenseAcquiredDate;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DutyAssignment> assignments;

    public Driver() {}

    public Driver(String name, LocalDate licenseAcquiredDate) {
        super(name);
        this.licenseAcquiredDate = licenseAcquiredDate;
    }

    @Transient
    public int getYearsOfExperience() {
        if (this.licenseAcquiredDate == null) {
            return 0;
        }
        return Period.between(this.licenseAcquiredDate, LocalDate.now()).getYears();
    }

    public LocalDate getLicenseAcquiredDate() {
        return licenseAcquiredDate;
    }

    public void setLicenseAcquiredDate(LocalDate licenseAcquiredDate) {
        this.licenseAcquiredDate = licenseAcquiredDate;
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }
    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }
}
