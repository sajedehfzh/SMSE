package polimi.it.smsSofwareEng.model;
import lombok.Data;
import javax.persistence.*;
@Entity
@Table(name = "result")
@Data
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Double score;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "academicCall")
    private AcademicCall academicCall;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student;



}
