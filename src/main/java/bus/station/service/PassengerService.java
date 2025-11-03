package bus.station.service;

import bus.station.model.Bus;
import bus.station.model.Passenger;
import bus.station.repository.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final PassengerRepo passengerRepo;

    @Autowired
    public PassengerService(PassengerRepo passengerRepo) {
        this.passengerRepo = passengerRepo;
    }

    public List<Passenger> findAllPassenger(){
        return passengerRepo.findAll();
    }

    public Optional<Passenger> findPassengerById(String id){
        return passengerRepo.findById(id);
    }

    public Passenger save(Passenger passenger) {
        return passengerRepo.save(passenger);
    }

    public void deleteById(String id) {
        passengerRepo.deleteById(id);
    }
}
