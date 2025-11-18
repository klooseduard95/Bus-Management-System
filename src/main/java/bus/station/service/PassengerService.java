package bus.station.service;

import bus.station.model.Passenger;
import bus.station.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> findAll(){
        return passengerRepository.findAll();
    }

    public Optional<Passenger> findById(Long id){
        return passengerRepository.findById(id);
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }
}
