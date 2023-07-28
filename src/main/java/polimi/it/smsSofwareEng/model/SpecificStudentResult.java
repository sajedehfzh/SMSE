package polimi.it.smsSofwareEng.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class SpecificStudentResult {

    private Long resultId;
    private Type type;
    private Double score;
    private Integer academicCall;
    private String academicYear;
}
