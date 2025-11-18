package bus.station.service;

import bus.station.model.Driver;
import bus.station.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Driver> findDriverById (Long id) {
        return driverRepository.findById(id);
    }

    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }
}
