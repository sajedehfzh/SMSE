package polimi.it.smsSofwareEng.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polimi.it.smsSofwareEng.model.*;
import polimi.it.smsSofwareEng.repository.*;
import polimi.it.smsSofwareEng.service.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;



   @Autowired
    private GeneralReportService generalReportService;

   @Autowired
   private AddResultService addResultService;

   @Autowired
   private ReportStudentResult reportStudentResult;

   @Autowired
    private AcademicCalenderService academicCalenderService;

@Autowired
private TeacherService teacherService;


    @PostMapping("/uploadExcel")
    public void uploadExcel(@RequestBody List<ResultDTO> resultDTOList) {
        addResultService.saveAll(resultDTOList);}

    @PostMapping("/addResult")
    void newResult(@RequestBody List<ResultDTO> ResultDTOs) {
        addResultService.saveAll(ResultDTOs);}



    @GetMapping("/reportResaut")
    List<Result> showResultList() {
        return resultRepository.findAll();
    }


    //@GetMapping("/reportAllResults")
   // List<GeneraResultDTO> getAllResults() {
       // return resultDTO.showAll();}

   // @PutMapping("/updateResult/{id}")
    //public void updateResultByStudentId(@RequestBody ResultDTO newResult,@PathVariable Long id)
    //{  newResult.update(newResult,id);
    //}


  /*  @DeleteMapping("/deleteResult/{id}")
    public String deleteReultBystudentId(@PathVariable Long id){

        for (Result result:studentRepository.findById(id).get().getResults())
            resultRepository.delete(result);
        studentRepository.deleteById(id);
        return "The student and results with id="+id+"is deleted successfully";
    }*/

    @GetMapping ("/getAcademicList")
    public List<AcademicYear> getAcademicList(){
        return  generalReportService.getAllAcademicYear();
    }

    @PostMapping ("/getGeneralReport")
    public  List<GeneraResultDTO> getGeneralResult(@RequestBody AcademicYear academicYear)
    {
       return generalReportService.showResultByAcademic(academicYear);
    }

    /*********************************student Result***********************/

            /*********************EditResult***************************/
    @GetMapping("/studentResult/{personCode}")
    public ResultListDTO showResultByPersonCode(@PathVariable Integer personCode){
        return reportStudentResult.showResultByPersonCode(personCode);
    }

    @PostMapping("/updateStudentResult")
    public void updateStudentResult(@RequestBody ResultListDTO resultList){
        reportStudentResult.EditResultByPerson(resultList);
    }

    /*****************************StudentInfo**********/

    @GetMapping("/showStudentInfo/{personCode}")
    public StudentDTO showStudentInfo(@PathVariable Integer personCode){
       return reportStudentResult.showStudentInfo(personCode);
    }
    @PostMapping("/updateStudentInfo")
    public void updateStudentInfo(@RequestBody StudentDTO studentDTO)
    {
        reportStudentResult.editStudentInfo(studentDTO);
    }


    /************************************AcademicCalenderService***************/

    @PostMapping("/createNewAcademicYear")
    public  void creatNewAcademicYear(@RequestBody Map<String, String> payload)
    {   String yearTitle = payload.get("year");
        academicCalenderService.createNewAcademicYear(yearTitle);
    }

    @PostMapping("/setCurentAcademicYear")
    public void setCurrentYear(@RequestBody Map<String, String> payload)
    {  String title=payload.get("academicYear");
        academicCalenderService.setCurrentYear(title);
    }


/**********************************show the current academic year ************************/

    @GetMapping("/showCurrentYear")
    public AcademicYear currentYear()
    {  return  generalReportService.getAllAcademicYear()
            .stream()
            .filter(academicYear -> academicYear.getYearStatus().equals(YearStatus.CURRENT)).findFirst().get();}
    /**********************login*****************************/

  /* @GetMapping("/login")
   // @PreAuthorize("isAuthenticated()")
    public UserDetails loadUserByUsername(@RequestBody  String userName){
        return  userLoginService.loadUserByUsername(userName);
    }*/


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Teacher user) {
        return teacherService.login(user.getUserName(),user.getPassword());
    }

  /*  @GetMapping("/login")
    public ResponseEntity<Teacher> login(@RequestBody Authentication authentication) {
        Teacher teacher = teacherService.findTeacherByUsername(authentication.getName());
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.status(401).build();
        }
    }*/



  /*  @PostMapping("/login")
    public String controllerLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/Home";
        } catch (AuthenticationException e) {
            return "redirect:/login?error=true";
        }
    }*/

}

