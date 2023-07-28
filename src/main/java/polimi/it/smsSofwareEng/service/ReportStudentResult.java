package polimi.it.smsSofwareEng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polimi.it.smsSofwareEng.model.*;
import polimi.it.smsSofwareEng.repository.AcademicYearRepository;
import polimi.it.smsSofwareEng.repository.CalenderRepository;
import polimi.it.smsSofwareEng.repository.ResultRepository;
import polimi.it.smsSofwareEng.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportStudentResult {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private CalenderRepository calenderRepository;

    @Autowired
    private ResultRepository resultRepository;

    public ResultListDTO showResultByPersonCode(Integer personCode){

        Student student=studentRepository.findByPersonCode(personCode);

        if(student==null)
            throw new RuntimeException("student with this score doesnt exist");

            ResultListDTO resultListDTO=new ResultListDTO();
            resultListDTO.setPersonCode(student.getPersonCode());
            resultListDTO.setStudentId(student.getId());
            resultListDTO.setFirstName(student.getFirstname());
            resultListDTO.setLastName(student.getLastname());

            List<SpecificStudentResult> specificStudentResults=new ArrayList<>();
            if (student.getResults()==null)
                throw new RuntimeException("student deons not have result in this sytem");
            for (Result result:student.getResults()){
                SpecificStudentResult specificStudentResult=new SpecificStudentResult();
                specificStudentResult.setAcademicCall(result.getAcademicCall().getCallExam());
                specificStudentResult.setType(result.getType());
                specificStudentResult.setAcademicYear(result.getAcademicCall().getAcademicYear().getYearTitle());
                specificStudentResult.setScore(result.getScore());
                specificStudentResult.setResultId(result.getId());
                specificStudentResults.add(specificStudentResult);

}
            resultListDTO.setResults(specificStudentResults);
            return resultListDTO;


        }


    public void EditResultByPerson(ResultListDTO newresult){

        Student student =studentRepository.findById(newresult.getStudentId()).get();
        List<Result> results=new ArrayList<>();
        List<Result> deletedResult=student.getResults();
        List<Result> remainRecord=student.getResults();
        remainRecord.retainAll(newresult.getResults());
        deletedResult.removeAll(remainRecord);
        resultRepository.deleteAll(deletedResult);
        for(SpecificStudentResult sresultDTO:newresult.getResults()){
            Result result=new Result();
            result.setId(sresultDTO.getResultId());
            result.setType(sresultDTO.getType());
            result.setStudent(student);
            result.setScore(sresultDTO.getScore());
            AcademicYear academicYear =academicYearRepository.findAcademicYearByYearTitle(sresultDTO.getAcademicYear());
            for (AcademicCall call:academicYear.getCallExam())
            {if (sresultDTO.getAcademicCall()==call.getCallExam())
                result.setAcademicCall(call);
            }
            result.setStatus(sresultDTO.getScore()>=9?Status.PASS:Status.FAIL);
            results.add(result);
           // resultRepository.save(result);
        }

        //student.setResults(results);
        student.getResults().addAll(results);
        studentRepository.save(student);

        }

        public  StudentDTO showStudentInfo(Integer personCode){

        Student student=studentRepository.findByPersonCode(personCode);
        if (student==null)
            throw new RuntimeException("this student doesnt exsist");
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setStudentCode(student.getPersonCode());
        studentDTO.setStudentId(student.getId());
        studentDTO.setFirstName(student.getFirstname());
        studentDTO.setLastName(student.getLastname());
        return studentDTO;
        }

        public void editStudentInfo(StudentDTO studentDTO){

        Student student=studentRepository.findById(studentDTO.getStudentId()).get();
        student.setId(studentDTO.getStudentId());
        student.setPersonCode(studentDTO.getStudentCode());
        student.setLastname(studentDTO.getLastName());
        student.setFirstname(studentDTO.getFirstName());
        studentRepository.save(student);


        }
}
