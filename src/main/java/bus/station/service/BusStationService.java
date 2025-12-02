package bus.station.service;

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
        // Check for duplicates
        if (busStation.getId() == null && busStationRepository.existsByName(busStation.getName())) {
            throw new IllegalArgumentException("Bus station with this name '" + busStation.getName() + "' already exists!");
        }
        if(busStation.getName().isEmpty() || busStation.getCity().isEmpty()) {
            throw new IllegalArgumentException("Bus station name and city must not be empty!");
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

            // LOGIC FIX: Changed && to ||.
            // We cannot delete if it is an Origin OR a Destination.
            boolean isOrigin = busStation.getRoutesAsOrigin() != null && !busStation.getRoutesAsOrigin().isEmpty();
            boolean isDest = busStation.getRoutesAsDestination() != null && !busStation.getRoutesAsDestination().isEmpty();

            if (isOrigin || isDest) {
                throw new RuntimeException("Cannot delete Bus Station. It is assigned to existing routes.");
            }
            busStationRepository.deleteById(id);
        }
    }
}