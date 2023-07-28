package polimi.it.smsSofwareEng.model;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "academic_call")
@Data
public class AcademicCall{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "academicYear")
    private AcademicYear academicYear;
    private Integer callExam;
    @OneToMany(mappedBy = "academicCall",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Result> results;
}
