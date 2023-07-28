package polimi.it.smsSofwareEng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polimi.it.smsSofwareEng.model.*;
import polimi.it.smsSofwareEng.repository.AcademicYearRepository;
import polimi.it.smsSofwareEng.repository.StudentRepository;
import polimi.it.smsSofwareEng.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddResultService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AcademicYearRepository academicYearRepository;

    @Autowired
    TeacherRepository teacherRepository;



    public void saveAll(List<ResultDTO> resultDTOs){
           for (ResultDTO resultDTO:resultDTOs)
               save(resultDTO);
    }

    public void save( ResultDTO resultDTO ){

       Student student= studentRepository.findByPersonCode(resultDTO.getPersonCode());
       if(student!=null) {

        AcademicYear currentYear= academicYearRepository.findAll().stream().
                filter(academicYear -> YearStatus.CURRENT
                        .equals(academicYear.getYearStatus())).findFirst().get();
        List<AcademicCall> currentCalls=currentYear.getCallExam();

        List<Result> studentResult=new ArrayList<>();
        if (resultDTO.getScoreRDD()!=0.0) {
            Result rdd = new Result();
            rdd.setType(Type.RDD);
            rdd.setScore(resultDTO.getScoreRDD());
            rdd.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallRDD()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreRDD() >= 9.0)
                rdd.setStatus(Status.PASS);
            else
                rdd.setStatus(Status.FAIL);

            rdd.setStudent(student);
            studentResult.add(rdd);
        }



         if (resultDTO.getScoreIT()!=0.0){
            Result it = new Result();
            it.setType(Type.IT);
            it.setScore(resultDTO.getScoreIT());
            it.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallIT()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreIT()>= 9.0)
                it.setStatus(Status.PASS);
            else
                it.setStatus(Status.FAIL);

            it.setStudent(student);
            studentResult.add(it);
        }
        if (resultDTO.getScoreWE1()!=0.0){
            Result we1 = new Result();
            we1.setType(Type.WE1);
            we1.setScore(resultDTO.getScoreWE1());
            we1.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallWE1()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreWE1() >= 9.0)
                we1.setStatus(Status.PASS);
            else
                we1.setStatus(Status.FAIL);

            we1.setStudent(student);
            studentResult.add(we1);
        }
        if (resultDTO.getScoreWE2()!=0.0){
            Result we2 = new Result();
            we2.setType(Type.WE2);
            we2.setScore(resultDTO.getScoreWE2());
            we2.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallWE2()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreWE2() >= 9.0)
                we2.setStatus(Status.PASS);
            else
                we2.setStatus(Status.FAIL);

            we2.setStudent(student);
            studentResult.add(we2);
        }
        if(resultDTO.getScoreRP()!=0.0){
            Result rp = new Result();
            rp.setType(Type.RP);
            rp.setScore(resultDTO.getScoreRP());
            rp.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallRP()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreRP()>= 9.0)
                rp.setStatus(Status.PASS);
            else
                rp.setStatus(Status.FAIL);

            rp.setStudent(student);
            studentResult.add(rp);
        }
        student.setTeacher(teacherRepository.findByLastname("Di Nitto"));
        student.getResults().addAll(studentResult);
        studentRepository.save(student);

    }
    else{
        Student newstudent=new Student();
        newstudent.setFirstname(resultDTO.getFirstname());
        newstudent.setLastname(resultDTO.getLastname());
        newstudent.setPersonCode(resultDTO.getPersonCode());



        AcademicYear currentYear= academicYearRepository.findAll().stream().
                filter(academicYear -> YearStatus.CURRENT
                        .equals(academicYear.getYearStatus())).findFirst().get();
        List<AcademicCall> currentCalls=currentYear.getCallExam();

        List<Result> studentResult=new ArrayList<>();
        if (resultDTO.getScoreRDD()!=0.0) {
            Result rdd = new Result();
            rdd.setType(Type.RDD);
            rdd.setScore(resultDTO.getScoreRDD());
            rdd.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallRDD()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreRDD() >= 9.0)
                rdd.setStatus(Status.PASS);
            else
                rdd.setStatus(Status.FAIL);
            rdd.setStudent(newstudent);
            studentResult.add(rdd);
        }



         if (resultDTO.getScoreIT()!=0.0){
            Result it = new Result();
            it.setType(Type.IT);
            it.setScore(resultDTO.getScoreIT());
            it.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallIT()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreIT()>= 9.0)
                it.setStatus(Status.PASS);
            else
                it.setStatus(Status.FAIL);
            it.setStudent(newstudent);
            studentResult.add(it);
        }
        if (resultDTO.getScoreWE1()!=0.0){
            Result we1 = new Result();
            we1.setType(Type.WE1);
            we1.setScore(resultDTO.getScoreWE1());
            we1.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallWE1()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreWE1() >= 9.0)
                we1.setStatus(Status.PASS);
            else
                we1.setStatus(Status.FAIL);
            we1.setStudent(newstudent);
            studentResult.add(we1);
        }
        if (resultDTO.getScoreWE2()!=0.0){
            Result we2 = new Result();
            we2.setType(Type.WE2);
            we2.setScore(resultDTO.getScoreWE2());
            we2.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallWE2()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreWE2() >= 9.0)
                we2.setStatus(Status.PASS);
            else
                we2.setStatus(Status.FAIL);
            we2.setStudent(newstudent);
            studentResult.add(we2);
        }
        if(resultDTO.getScoreRP()!=0.0){
            Result rp = new Result();
            rp.setType(Type.RP);
            rp.setScore(resultDTO.getScoreRP());
            rp.setAcademicCall(currentCalls.stream()
                    .filter(academicCall -> resultDTO.getCallRP()
                            .equals(academicCall.getCallExam())).findFirst().get());
            if (resultDTO.getScoreRP()>= 9.0)
                rp.setStatus(Status.PASS);
            else
                rp.setStatus(Status.FAIL);
            rp.setStudent(newstudent);
            studentResult.add(rp);
        }
        newstudent.setTeacher(teacherRepository.findByLastname("Di Nitto"));
       // newstudent.getResults().addAll(studentResult);
        newstudent.setResults(studentResult);
        studentRepository.save(newstudent);

    }
    }}

