package bus.station.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "bus_stations")
public class BusStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Station name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters long")
    private String name;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<Route> routesAsOrigin;

    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private List<Route> routesAsDestination;

    public BusStation() {}

    public BusStation(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Route> getRoutesAsOrigin() {
        return routesAsOrigin;
    }

    public void setRoutesAsOrigin(List<Route> routesAsOrigin) {
        this.routesAsOrigin = routesAsOrigin;
    }

    public List<Route> getRoutesAsDestination() {
        return routesAsDestination;
    }

    public void setRoutesAsDestination(List<Route> routesAsDestination) {
        this.routesAsDestination = routesAsDestination;
    }
}
