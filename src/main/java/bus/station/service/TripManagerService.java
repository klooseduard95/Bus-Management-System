package bus.station.service;

import bus.station.model.TripManager;
import bus.station.repository.TripManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripManagerService {

    private final TripManagerRepository tripManagerRepository;
    @Autowired
    public TripManagerService(TripManagerRepository tripManagerRepository) {
        this.tripManagerRepository = tripManagerRepository;
    }

    public List<TripManager> findAll() {
        return tripManagerRepository.findAll();
    }

    public Optional<TripManager> findById(Long id) {
        return  tripManagerRepository.findById(id);
    }

    public TripManager save(TripManager tripManager) {
        return tripManagerRepository.save(tripManager);
    }

    public void deleteById(Long id) {
        tripManagerRepository.deleteById(id);
    }
}
