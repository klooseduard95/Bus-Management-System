package bus.station.service;

import bus.station.model.Passenger;
import bus.station.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        if(passenger.getName().isEmpty() || passenger.getCurrency().isEmpty()){
            throw new IllegalArgumentException("Passenger name and currency must be filled.");
        }

        if(passenger.getDateOfBirth().isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException("Passenger date must be before current date.");
        }


        return passengerRepository.save(passenger);
    }

    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }
}
