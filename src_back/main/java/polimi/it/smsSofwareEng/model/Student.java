package polimi.it.smsSofwareEng.model;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private Integer personCode;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Result> results;


}
