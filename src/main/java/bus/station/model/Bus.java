package bus.station.model;

import bus.station.enums.BusStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

import java.time.LocalDate;

@Entity
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Registration number is required")
    @Size(min = 3, max = 15, message = "Registration number must be between 3 and 15 characters long")
    private String registrationNumber;

    @NotNull(message =  "Capacity is required")
    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    @Enumerated(EnumType.STRING)
    private BusStatus status;

    @PastOrPresent(message = "Mast maintenance date must be in the past or present")
    private LocalDate lastMaintenanceDate;

    private boolean hasAccessibilitySupport;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    private List<BusTrip> busTrips;

    public Bus() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHasAccessibilitySupport() {
        return hasAccessibilitySupport;
    }

    public void setHasAccessibilitySupport(boolean hasAccessibilitySupport) {
        this.hasAccessibilitySupport = hasAccessibilitySupport;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BusStatus getStatus() {
        return status;
    }

    public void setStatus(BusStatus status) {
        this.status = status;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public List<BusTrip> getBusTrips() {
        return busTrips;
    }

    public void setBusTrips(List<BusTrip> busTrips) {
        this.busTrips = busTrips;
    }
}
