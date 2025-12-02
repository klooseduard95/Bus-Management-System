package bus.station.service;

import bus.station.model.TripManager;
import bus.station.repository.TripManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Trip Manager ID must be non-null and positive.");
        }
        return  tripManagerRepository.findById(id);
    }

    @Transactional
    public TripManager save(TripManager tripManager) {
        if (tripManager == null) {
            throw new IllegalArgumentException("Trip Manager object cannot be null.");
        }

        String name = tripManager.getName();
        String employeeCode = tripManager.getEmployeeCode();

        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name must not be null or empty.");
        }

        if (name.length() < 3 || name.length() > 100) {
            throw new IllegalArgumentException("Name must be between 3 and 100 characters.");
        }

        if (employeeCode == null || employeeCode.trim().isEmpty()){
            throw new IllegalArgumentException("Employee code must not be null or empty.");
        }

        if (employeeCode.length() < 3 || employeeCode.length() > 20) {
            throw new IllegalArgumentException("Employee code must be between 3 and 20 characters.");
        }

        if (tripManager.getId() == null && tripManagerRepository.existsByEmployeeCode(employeeCode)) {
            throw new IllegalArgumentException("Trip Manager with employee code " + employeeCode + " already exists.");
        }

        return tripManagerRepository.save(tripManager);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Trip Manager ID must be non-null and positive.");
        }

        Optional<TripManager> managerOpt = tripManagerRepository.findById(id);

        if (managerOpt.isPresent()) {
            TripManager manager = managerOpt.get();

            if (manager.getAssignments() != null && !manager.getAssignments().isEmpty()) {
                throw new RuntimeException("Cannot delete trip manager. They have associated duty assignments.");
            }

            tripManagerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Trip Manager with ID " + id + " not found for deletion.");
        }
    }
}