package bus.station.service;

import bus.station.model.BusStation;
import bus.station.repository.BusStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public BusStation save(BusStation busStation) {
        if (busStation.getName() == null || busStation.getName().isEmpty()) {
            throw new IllegalArgumentException("Station name is required");
        }
        return busStationRepository.save(busStation);
    }

    public Optional<BusStation> findById(Long id) {
        return busStationRepository.findById(id);
    }

    public void deleteById(Long id) {
        busStationRepository.deleteById(id);
    }

}
