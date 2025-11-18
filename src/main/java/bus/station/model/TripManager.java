package bus.station.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@DiscriminatorValue("MANAGER")
public class TripManager extends Staff {

    @Column(name = "employee_code")
    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    public TripManager() {
        super();
    }

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
}
