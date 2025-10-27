package bus.station.model;

import bus.station.enums.BusStatus;
import bus.station.interfaces.Identifiable;

import java.time.LocalDate;

public class Bus implements Identifiable {
    private String id;
    private String registrationNumber;
    private int capacity;
    private BusStatus status;
    private String model;
    private LocalDate lastMaintenanceDate;

    public Bus(String id, String registrationNumber, int capacity, BusStatus status, String model, LocalDate lastMaintenanceDate) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
        this.status = status;
        this.model = model;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
}
