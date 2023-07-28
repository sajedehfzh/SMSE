package polimi.it.smsSofwareEng.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polimi.it.smsSofwareEng.model.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Teacher findByUserNameAndPassword(String userName, String password);
    Teacher findByLastname(String lastname);

   // Optional<Teacher> findByUserName(String userName);
    Teacher findByUserName(String userName);
}
