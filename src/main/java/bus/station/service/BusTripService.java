package bus.station.service;

import bus.station.model.BusTrip;
import bus.station.repository.BusTripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusTripService {
    private final BusTripRepo busTripRepo;

    @Autowired
    public BusTripService(BusTripRepo busTripRepo) {
        this.busTripRepo = busTripRepo;
    }

    public List<BusTrip> findAll() {
        return busTripRepo.findAll();
    }

    public Optional<BusTrip> findBusTripById(String id) {
        return busTripRepo.findById(id);
    }
}
