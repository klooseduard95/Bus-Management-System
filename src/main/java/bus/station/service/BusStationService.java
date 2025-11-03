package bus.station.service;

import bus.station.model.BusStation;
import bus.station.repository.BusStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusStationService {
    private final BusStationRepo busStationRepo;

    @Autowired
    public BusStationService(BusStationRepo busStationRepo) {
        this.busStationRepo = busStationRepo;
    }

    public List<BusStation> findAll() {
        return busStationRepo.findAll();
    }

    public BusStation save(BusStation busStation) {
        if (busStation.getName() == null || busStation.getName().isEmpty()) {
            throw new IllegalArgumentException("Station name is required");
        }
        return busStationRepo.save(busStation);
    }

    public Optional<BusStation> findById(String id) {
        return busStationRepo.findById(id);
    }

    public boolean deleteById(String id) {
        return busStationRepo.deleteById(id);
    }

}
