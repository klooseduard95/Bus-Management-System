package bus.station.service;

import bus.station.model.Bus;
import bus.station.repository.BusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private final BusRepo busRepo;
    @Autowired
    public BusService(BusRepo busRepo) {
        this.busRepo = busRepo;
    }

    public List<Bus> findAll() {
        return busRepo.findAll();
    }

    public Optional<Bus> findBusById(String id) {
        return busRepo.findById(id);
    }
}
