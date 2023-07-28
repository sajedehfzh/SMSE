package polimi.it.smsSofwareEng.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor

public class ResultListDTO {

  private Long studentId;
  private String firstName;
  private String lastName;
  private Integer personCode;
  private List<SpecificStudentResult> results;

}
