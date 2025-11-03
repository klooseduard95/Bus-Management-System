package bus.station.service;

import bus.station.model.Staff;
import bus.station.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepo staffRepo;

    @Autowired
    public StaffService(StaffRepo staffRepo) {
        this.staffRepo = staffRepo;
    }

    public Optional<Staff> findById(String id){
        return staffRepo.findById(id);
    }
    public List<Staff> findAll(){
        return staffRepo.findAll();
    }

    public Staff save(Staff staff){
        return staffRepo.save(staff);
    }
    public boolean delete(String id){
        return staffRepo.deleteById(id);
    }
}
