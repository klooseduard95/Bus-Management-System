package bus.station.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "staff")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staff_type")
public abstract class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Staff name is required")
    private String name;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<DutyAssignment> assignments;

    public Staff() {}

    public Staff(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DutyAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<DutyAssignment> assignments) {
        this.assignments = assignments;
    }
}
