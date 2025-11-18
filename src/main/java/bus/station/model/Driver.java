package bus.station.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@DiscriminatorValue("DRIVER")
public class Driver extends Staff {
    @Column(name = "license_acquired_date")
    @NotNull(message = "License acquired date is missing")
    @Past(message = "License date must be in the past")
    private LocalDate licenseAcquiredDate;

    public Driver() {
        super();
    }

    public Driver(String name, LocalDate licenseAcquiredDate) {
        super(name);
        this.licenseAcquiredDate = licenseAcquiredDate;
    }

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
}
