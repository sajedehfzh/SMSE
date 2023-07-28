package polimi.it.smsSofwareEng.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class GeneraResultDTO {

  // private Long resultId;
    private String studentFirstName;
    private String studentLastName;
    private Integer studentCode;
    private String academicYear;
    private Double scoreWE1=0.0;
    private Integer callWE;
    private Double scoreWE2=0.0;
     private Integer callWE2;
    private Double scoreIT=0.0;
    private Integer callIT;
    private Double scoreRDD=0.0;
    private Integer callRDD;
    private Double scoreRP=0.0;
    private Integer callRP;
    private Double score=0.0;
    private Status status;



}
