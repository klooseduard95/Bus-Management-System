package bus.station.service;

import bus.station.model.BusTrip;
import bus.station.repository.BusTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public BusTrip save(BusTrip busTrip) {
        if (busTrip.getBus() == null || busTrip.getRoute() == null) {
            throw new IllegalArgumentException("Bus and Route must be valid");
        }
        return busTripRepository.save(busTrip);
    }

    public Optional<BusTrip> findById(Long id) {
        return busTripRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<BusTrip> busTripOpt = busTripRepository.findById(id);

        if(busTripOpt.isPresent()) {
            BusTrip busTrip = busTripOpt.get();

            // FIX: Check !isEmpty(), not just != null
            if(busTrip.getTickets() != null && !busTrip.getTickets().isEmpty()) {
                throw new RuntimeException("Cannot delete Trip. It has issued tickets.");
            }

            busTripRepository.deleteById(id);
        }
    }
}