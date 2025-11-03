package bus.station.controller;

import bus.station.model.Staff;
import bus.station.service.StaffService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping("/")
    public List<Staff> getAllStaff(){
        return staffService.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<Staff> findByid(@PathVariable String id){
        return staffService.findById(id);
    }
}
