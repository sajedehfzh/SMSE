package polimi.it.smsSofwareEng.model;


import lombok.Data;


        @Data

        public class ResultDTO {


                private Integer personCode;
                private String firstname;
                private String lastname;
                private Double scoreWE1=0.0;
                private Integer callWE1;
                private Double scoreWE2=0.0;
                private Integer callWE2;
                private Double scoreIT=0.0;
                private Integer callIT;
                private Double scoreRDD=0.0;
                private Integer callRDD;
                private Double scoreRP=0.0;
                private Integer callRP;



    /*  public void update(ResultDTO newresultDTO,Long id){

          if(!studentRepository.existsById(id))
              throw new UserNotFoundException(id);

          Student student=studentRepository.findById(id).get();
          student.setFirstname(newresultDTO.getStudent().getFirstname());
          student.setLastname(newresultDTO.getStudent().getLastname());
          student.setPersonCode(newresultDTO.getStudent().getPersonCode());

          List<Result> studentResult=new ArrayList<>();
          if (newresultDTO.sRDD!=0.0) {
              Result rdd = new Result();
              rdd.setType(Type.RDD);
              rdd.setScore(newresultDTO.sRDD);
              //rdd.setAcademicCall(
              if (newresultDTO.sRDD >= 9.0)
                  rdd.setStatus(Status.PASS);
              else
                  rdd.setStatus(Status.FAIL);
              studentResult.add(rdd);
          }



          else if (newresultDTO.sIT!=0.0){
              Result it = new Result();
              it.setType(Type.IT);
              it.setScore(newresultDTO.sIT);
              //rdd.setAcademicCall(
              if (newresultDTO.sIT >= 9.0)
                  it.setStatus(Status.PASS);
              else
                  it.setStatus(Status.FAIL);

              studentResult.add(it);
          }
          if (newresultDTO.sWE1!=0.0){
              Result it = new Result();
              it.setType(Type.IT);
              it.setScore(newresultDTO.sIT);
              //rdd.setAcademicCall(
              if (newresultDTO.sIT >= 9.0)
                  it.setStatus(Status.PASS);
              else
                  it.setStatus(Status.FAIL);

              studentResult.add(it);
          }
          if (newresultDTO.sWE1!=0.0){
              Result we1 = new Result();
              we1.setType(Type.WE1);
              we1.setScore(newresultDTO.sWE1);
              //rdd.setAcademicCall(
              if (newresultDTO.sWE1 >= 9.0)
                  we1.setStatus(Status.PASS);
              else
                  we1.setStatus(Status.FAIL);

              studentResult.add(we1);
          }
          if(newresultDTO.sRP!=0.0){
              Result rp = new Result();
              rp.setType(Type.RP);
              rp.setScore(newresultDTO.sRP);
              //rdd.setAcademicCall(
              if (newresultDTO.sRP>= 9.0)
                  rp.setStatus(Status.PASS);
              else
                  rp.setStatus(Status.FAIL);

              studentResult.add(rp);
          }

          student.setResults(studentResult);

          studentRepository.save(student);
      }

      public List<GeneraResultDTO> showAll(){
          List<Student> studentList = studentRepository.findAll();
          List<GeneraResultDTO> resultDTOList = new ArrayList<>();

          for (Student student : studentList) {
              GeneraResultDTO resultDTO = new GeneraResultDTO();
              resultDTO.setStudentLastName(student.getLastname());
              resultDTO.setStudentFirstName(student.getFirstname());
              resultDTO.setStudentCode(student.getPersonCode());
              List<Result> passResult=student.getResults().stream().filter(result -> result.getStatus()== Status.PASS).toList();
              for(Result result:passResult)
              {
                  resultDTO.setScore(resultDTO.getScore()+result.getScore());
              }
              if (resultDTO.getScore()>=18.0)
                  resultDTO.setStatus(Status.PASS);
              else
                  resultDTO.setStatus(Status.FAIL);

              for (Result result : student.getResults()) {

                  switch (result.getType()) {
                      case IT:
                          resultDTO.setIT(result.getScore());
                          break;
                      case RDD:
                          resultDTO.setRDD(result.getScore());
                          break;
                      case WE1:
                          resultDTO.setWE1(result.getScore());
                          break;
                      case RP:
                          resultDTO.setRP(result.getScore());
                          break;
                      case WE2:
                          resultDTO.setWE2(result.getScore());
                          break;
                  }
                  resultDTO.setAcademicYear(result.getAcademicCall().getAcademicYear().getAcademic_Year());

              }
              resultDTOList.add(resultDTO);
          }
          return resultDTOList;}


     */
}
