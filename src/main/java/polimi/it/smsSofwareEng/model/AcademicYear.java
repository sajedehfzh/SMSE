package polimi.it.smsSofwareEng.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "academic_year")
@Data
@NoArgsConstructor
@JsonIgnoreProperties("callExam")
public class AcademicYear {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private Long code;
        @Enumerated(EnumType.STRING)
        private YearStatus yearStatus;
        private String yearTitle;
        @OneToMany(mappedBy = "academicYear",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
        private List<AcademicCall> callExam;
    }

