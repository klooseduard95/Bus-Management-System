package bus.station.service;

import bus.station.model.Bus;
import bus.station.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Bus> findBusById(String id) {
        return busRepository.findById(id);
    }

    public Bus save(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteById(String id) {
        busRepository.deleteById(id);
    }

}
