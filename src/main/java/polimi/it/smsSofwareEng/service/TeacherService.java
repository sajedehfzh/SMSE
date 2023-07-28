package polimi.it.smsSofwareEng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import polimi.it.smsSofwareEng.model.Teacher;
import polimi.it.smsSofwareEng.repository.TeacherRepository;
@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public  ResponseEntity<String> login(String userName,String password) {
    Teacher professor = teacherRepository.findByUserNameAndPassword(userName, password);
        if (professor != null) {
        return ResponseEntity.ok("Login successful");
    } else {
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
    }

}
