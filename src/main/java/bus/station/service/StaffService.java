package bus.station.service;

import bus.station.model.Staff;
import bus.station.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Optional<Staff> findById(String id){
        return staffRepository.findById(id);
    }
    public List<Staff> findAll(){
        return staffRepository.findAll();
    }

    public Staff save(Staff staff){
        return staffRepository.save(staff);
    }
    public boolean delete(String id){
        return staffRepository.deleteById(id);
    }
}
