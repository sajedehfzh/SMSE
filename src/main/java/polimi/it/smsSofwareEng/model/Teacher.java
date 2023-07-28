package polimi.it.smsSofwareEng.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String userName;
    private  String password;
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Student> student;
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
