package polimi.it.smsSofwareEng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polimi.it.smsSofwareEng.model.*;
import polimi.it.smsSofwareEng.repository.AcademicYearRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class GeneralReportService {
    @Autowired
    private AcademicYearRepository academicYearRepository;


    public List<AcademicYear> getAllAcademicYear(){
        List<AcademicYear> list =academicYearRepository.findAll();
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("Academic year list is null or empty.");}
        return list;
    }



   public List<GeneraResultDTO> showResultByAcademic(AcademicYear academicYear1){
        AcademicYear academicYear=academicYearRepository.findAcademicYearByYearTitle(academicYear1.getYearTitle());
        if(academicYear.getCallExam()==null)
           throw new RuntimeException("selected academic year dont define the call exam");

        List<GeneraResultDTO> reportList=new ArrayList<>();

        for (AcademicCall call:academicYear.getCallExam())
        {for (Result result: call.getResults()){
            Boolean isExist=reportList.stream().anyMatch(report -> report.getStudentCode().equals(result.getStudent().getPersonCode()));
                if (isExist) {


                    GeneraResultDTO generaResultDTO = reportList.stream()
                            .filter(generaResultDTO1 -> generaResultDTO1.getStudentCode()
                                    .equals(result.getStudent().getPersonCode())).findFirst().get();

                    Double tscore=0.0;

                    if (result.getType().equals(Type.WE1)) {
                        Integer maxCall = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE1))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).mapToInt(result1->result1.getAcademicCall().getCallExam()).max().orElse(0);
                        Double score = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE1))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).filter(result1 -> result1.getAcademicCall().getCallExam().equals(maxCall)).findFirst().get().getScore();
                        generaResultDTO.setScoreWE1(score);
                        if (score>=9.0)
                            tscore+=score;
                        generaResultDTO.setCallWE(result.getAcademicCall().getCallExam());
                    }
                    if (result.getType().equals(Type.WE2)) {

                        Integer maxCall = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE2))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).mapToInt(result1->result1.getAcademicCall().getCallExam()).max().orElse(0);
                        Double score = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE2))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).filter(result1 -> result1.getAcademicCall().getCallExam().equals(maxCall)).findFirst().get().getScore();
                        generaResultDTO.setScoreWE2(score);
                        if (score>=9.0)
                            tscore+=score;
                        generaResultDTO.setCallWE2(result.getAcademicCall().getCallExam());
                    }

                    if (result.getType().equals(Type.IT)) {
                        generaResultDTO.setScoreIT(result.getScore());
                        if(result.getScore()>=9.0)
                            tscore+=result.getScore();

                        generaResultDTO.setCallIT(result.getAcademicCall().getCallExam());
                    }

                    if (result.getType().equals(Type.RP)) {
                        generaResultDTO.setScoreRP(result.getScore());
                        if(result.getScore()>=9.0)
                            tscore+=result.getScore();
                        generaResultDTO.setCallRP(result.getAcademicCall().getCallExam());}


                        if (result.getType().equals(Type.RDD)) {

                            generaResultDTO.setScoreRDD(result.getScore());
                            if(result.getScore()>=9.0)
                                tscore+=result.getScore();
                            generaResultDTO.setCallRDD(result.getAcademicCall().getCallExam());
                        }

                    generaResultDTO.setScore(generaResultDTO.getScore()+tscore);
                        if(generaResultDTO.getScore()>=18.0)
                            generaResultDTO.setStatus(Status.PASS);
                        else
                            generaResultDTO.setStatus(Status.FAIL);
                    //reportList.add(generaResultDTO);


                }
             else {
                GeneraResultDTO generalReport=new GeneraResultDTO();
            generalReport.setStudentFirstName(result.getStudent().getFirstname());
            generalReport.setStudentCode(result.getStudent().getPersonCode());
            generalReport.setStudentLastName(result.getStudent().getLastname());
            Double tscore=0.0;
                    if(result.getType().equals(Type.WE1)){
                        Integer maxCall = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE1))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).mapToInt(result1->result1.getAcademicCall().getCallExam()).max().orElse(0);
                        Double score = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE1))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).filter(result1 -> result1.getAcademicCall().getCallExam().equals(maxCall)).findFirst().get().getScore();
                        generalReport.setScoreWE1(score);
                        if (score>=9.0)
                            tscore+=score;
                        generalReport.setCallWE(result.getAcademicCall().getCallExam());
                    }
                    if(result.getType().equals(Type.WE2)){
                        Integer maxCall = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE2))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).mapToInt(result1->result1.getAcademicCall().getCallExam()).max().orElse(0);
                        Double score = result.getStudent().getResults().stream()
                                .filter(result1 -> result1.getType().equals(Type.WE2))
                                .filter(result1 -> result1.getAcademicCall() .getAcademicYear().getYearTitle().equals(academicYear.getYearTitle())).filter(result1 -> result1.getAcademicCall().getCallExam().equals(maxCall)).findFirst().get().getScore();
                        generalReport.setScoreWE2(score);
                        if (score>=9.0)
                            tscore+=score;
                        generalReport.setCallWE2(result.getAcademicCall().getCallExam());
                    }

                    if (result.getType().equals(Type.IT)){
                        generalReport.setScoreIT(result.getScore());
                        if(result.getScore()>=9.0)
                            tscore+=result.getScore();
                        generalReport.setCallIT(result.getAcademicCall().getCallExam());
                    }

                    if (result.getType().equals(Type.RP)){
                        generalReport.setScoreRP(result.getScore());
                        if(result.getScore()>=9.0)
                            tscore+=result.getScore();
                        generalReport.setCallRP(result.getAcademicCall().getCallExam());}


                        if (result.getType().equals(Type.RDD)){

                            generalReport.setScoreRDD(result.getScore());
                            if(result.getScore()>=9.0)
                                tscore+=result.getScore();
                            generalReport.setCallRDD(result.getAcademicCall().getCallExam());
                        }
                     generalReport.setScore(generalReport.getScore()+tscore);
                    if(generalReport.getScore()>=18.0)
                        generalReport.setStatus(Status.PASS);
                    else
                        generalReport.setStatus(Status.FAIL);
                    reportList.add(generalReport);
        }}
        }
    return reportList;
}}
