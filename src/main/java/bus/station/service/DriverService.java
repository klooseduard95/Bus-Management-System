package bus.station.service;

import bus.station.model.Driver;
import bus.station.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Driver ID must be non-null and positive.");
        }
        return driverRepository.findById(id);
    }

    @Transactional
    public void save(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver object cannot be null.");
        }

        String driverName = driver.getName();

        if (driverName == null || driverName.trim().isEmpty()){
            throw new IllegalArgumentException("Driver name must not be null or empty.");
        }

        if (driverName.length() < 3 || driverName.length() > 100) {
            throw new IllegalArgumentException("Driver name must be between 3 and 100 characters.");
        }

        if (driver.getLicenseAcquiredDate() == null) {
            throw new IllegalArgumentException("Driver license acquired date must not be null.");
        }

        if (driver.getLicenseAcquiredDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Driver license must be acquired before today.");
        }


        driverRepository.save(driver);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Driver ID must be non-null and positive.");
        }

        Optional<Driver> driverOpt = driverRepository.findById(id);

        if (driverOpt.isPresent()) {
            Driver driver = driverOpt.get();

            if (driver.getAssignments() != null && !driver.getAssignments().isEmpty()) {
                throw new RuntimeException("Cannot delete driver. They are currently assigned to existing trips.");
            }

            driverRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Driver with ID " + id + " not found for deletion.");
        }
    }
}