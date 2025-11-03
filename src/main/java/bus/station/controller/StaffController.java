package bus.station.controller;

import bus.station.model.Staff;
import bus.station.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/")
    public List<Staff> getAllStaff(){
        return staffService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Staff> findByid(@PathVariable String id){
        return staffService.findById(id);
    }
}
