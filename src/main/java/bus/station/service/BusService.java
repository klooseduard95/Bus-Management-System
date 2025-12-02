package bus.station.service;

import bus.station.model.Bus;
import bus.station.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private final BusRepository busRepository;
    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    public Optional<Bus> findBusById(Long id) {
        return busRepository.findById(id);
    }
@Transactional
    public Bus save(Bus bus) {
        if (bus.getId() == null && busRepository.existsByRegistrationNumber(bus.getRegistrationNumber())) {
            throw new IllegalArgumentException("Bus with registration number " + bus.getRegistrationNumber() + " already exists!");
        }

        if(bus.getCapacity() < 0)
        {
            throw new IllegalArgumentException("Bus capacity less than 0");
        }

        if(bus.getLastMaintenanceDate() == null ||  bus.getLastMaintenanceDate().isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException("Bus last maintenance date must be past or present!");
        }
        return busRepository.save(bus);
    }
@Transactional
    public void deleteById(Long id) {
        Optional<Bus> busOpt = busRepository.findById(id);
        if (busOpt.isPresent()) {
            Bus bus = busOpt.get();
            if (bus.getBusTrips() != null && !bus.getBusTrips().isEmpty()) {
                throw new RuntimeException("Cannot delete bus. It is assigned to existing trips.");
            }
            busRepository.deleteById(id);
        }
    }

}
