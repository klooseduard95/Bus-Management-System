package bus.station.service;

import bus.station.model.Passenger;
import bus.station.repository.PassengerRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public List<Passenger> findAll(String name, Boolean specialAssistance, String sortField, String sortDir) {

        Specification<Passenger> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(name)) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (specialAssistance != null) {
                predicates.add(cb.equal(root.get("requiresSpecialAssistance"), specialAssistance));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = Sort.by(sortField);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return passengerRepository.findAll(spec, sort);
    }

    public Optional<Passenger> findById(Long id){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Passenger ID must be non-null and positive.");
        }
        return passengerRepository.findById(id);
    }

    @Transactional
    public Passenger save(Passenger passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger object cannot be null.");
        }

        String name = passenger.getName();
        String currency = passenger.getCurrency();
        LocalDate dateOfBirth = passenger.getDateOfBirth();

        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Passenger name must not be null or empty.");
        }

        if (name.length() < 3 || name.length() > 100) {
            throw new IllegalArgumentException("Passenger name must be between 3 and 100 characters.");
        }

        if (currency == null || currency.trim().isEmpty()){
            throw new IllegalArgumentException("Passenger currency must not be null or empty.");
        }

        if (currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter ISO code (e.g., EUR, RON).");
        }

        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Passenger date of birth must not be null.");
        }

        if(dateOfBirth.isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException("Passenger date of birth must be past or present.");
        }

        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
        }

        return passengerRepository.save(passenger);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Passenger ID must be non-null and positive.");
        }

        Optional<Passenger> passengerOpt = passengerRepository.findById(id);

        if (passengerOpt.isPresent()) {
            Passenger passenger = passengerOpt.get();

            if (passenger.getTickets() != null && !passenger.getTickets().isEmpty()) {
                throw new RuntimeException("Cannot delete passenger. They have associated tickets.");
            }

            passengerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Passenger with ID " + id + " not found for deletion.");
        }
    }
}