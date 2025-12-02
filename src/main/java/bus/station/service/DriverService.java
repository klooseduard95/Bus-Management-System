package bus.station.service;

import bus.station.model.Driver;
import bus.station.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Optional<Driver> findById (Long id) {
        return driverRepository.findById(id);
    }

    public void save(Driver driver) {
        if(driver.getName().isEmpty()){
            throw new IllegalArgumentException("Driver name must not be empty.");
        }
        if(driver.getLicenseAcquiredDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Driver license must be acquired before today");
        }

        driverRepository.save(driver);
    }

    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }
}
