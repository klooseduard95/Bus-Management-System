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

    public Optional<BusStation> findBusStationById(String id) {
        return busStationRepo.findById(id);
    }

}
