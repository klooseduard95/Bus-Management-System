package bus.station.service;

import bus.station.model.Bus;
import bus.station.model.BusStation;
import bus.station.repository.BusStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BusStationService {
    private final BusStationRepository busStationRepository;

    @Autowired
    public BusStationService(BusStationRepository busStationRepository) {
        this.busStationRepository = busStationRepository;
    }

    public List<BusStation> findAll() {
        return busStationRepository.findAll();
    }
@Transactional
    public BusStation save(BusStation busStation) {
        if (busStation.getId() == null && busStationRepository.existsByName(busStation.getName())) {
            throw new IllegalArgumentException("Bus station with this name " + busStation.getName() + " already exists!");
        }
        return busStationRepository.save(busStation);
    }

    public Optional<BusStation> findById(Long id) {
        return busStationRepository.findById(id);
    }
@Transactional
    public void deleteById(Long id) {
        Optional<BusStation> busStationOpt = busStationRepository.findById(id);
        if (busStationOpt.isPresent()) {
            BusStation busStation = busStationOpt.get();
            if (busStation.getRoutesAsOrigin() != null && !busStation.getRoutesAsOrigin().isEmpty() && busStation.getRoutesAsDestination() != null && !busStation.getRoutesAsDestination().isEmpty()) {
                throw new RuntimeException("Cannot delete busStation. It is assigned to existing routes.");
            }
            busStationRepository.deleteById(id);
        }
    }

}
