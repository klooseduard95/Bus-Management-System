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

    public BusTrip save(BusTrip busTrip) {
        if (busTrip.getAvailableSeats() < 0) {
            busTrip.setAvailableSeats(0);
        }
        return busTripRepo.save(busTrip);
    }

    public Optional<BusTrip> findById(String id) {
        return busTripRepo.findById(id);
    }

    public void deleteById(String id) {
        busTripRepo.deleteById(id);
    }
}
