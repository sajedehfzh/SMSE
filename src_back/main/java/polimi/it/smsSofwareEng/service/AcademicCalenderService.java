package polimi.it.smsSofwareEng.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polimi.it.smsSofwareEng.model.AcademicCall;
import polimi.it.smsSofwareEng.model.AcademicYear;
import polimi.it.smsSofwareEng.model.YearStatus;
import polimi.it.smsSofwareEng.repository.AcademicYearRepository;
import polimi.it.smsSofwareEng.repository.CalenderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicCalenderService {

    @Autowired
    AcademicYearRepository academicYearRepository;

    @Autowired
    CalenderRepository academicCall;



    public void setCurrentYear(String title){
        //Long id;
       // String title="2023-24";
        List<AcademicYear> academicCallList=academicYearRepository.findAll();
        AcademicYear oldcurentAcademicYear=academicCallList.stream().filter(academicYear -> academicYear.getYearStatus().equals(YearStatus.CURRENT)).findFirst().get();
        oldcurentAcademicYear.setYearStatus(YearStatus.PAST);
        AcademicYear newcurentAcademicYear=academicYearRepository.findAcademicYearByYearTitle(title);
        newcurentAcademicYear.setYearStatus(YearStatus.CURRENT);
        academicYearRepository.save(oldcurentAcademicYear);
        academicYearRepository.save(newcurentAcademicYear);
    }

    public void createNewAcademicYear(String title){
        List<AcademicYear> academicYearList=academicYearRepository.findAll();
        Long maxCode = academicYearList.stream()
                .mapToLong(AcademicYear::getCode)
                .max()
                .orElse(0);

        AcademicYear academicYear=new AcademicYear();
        academicYear.setYearTitle(title);
        academicYear.setCode(maxCode+1);
        List<AcademicCall> academicCallList=new ArrayList<>();
        for (int i = 1;i<=5 ; i++)
        {
            AcademicCall academicCall1=new AcademicCall();
            academicCall1.setAcademicYear(academicYear);
            academicCall1.setCallExam(i);
            academicCallList.add(academicCall1);
        }



        academicYear.setCallExam(academicCallList);
        academicYearRepository.save(academicYear);
    }
}
