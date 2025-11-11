package bus.station.service;

import bus.station.model.BusTrip;
import bus.station.repository.BusTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusTripService {
    private final BusTripRepository busTripRepository;

    @Autowired
    public BusTripService(BusTripRepository busTripRepository) {
        this.busTripRepository = busTripRepository;
    }

    public List<BusTrip> findAll() {
        return busTripRepository.findAll();
    }

    public BusTrip save(BusTrip busTrip) {
        if (busTrip.getAvailableSeats() < 0) {
            busTrip.setAvailableSeats(0);
        }
        return busTripRepository.save(busTrip);
    }

    public Optional<BusTrip> findById(String id) {
        return busTripRepository.findById(id);
    }

    public void deleteById(String id) {
        busTripRepository.deleteById(id);
    }
}
