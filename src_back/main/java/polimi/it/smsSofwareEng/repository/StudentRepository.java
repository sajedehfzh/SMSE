package polimi.it.smsSofwareEng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polimi.it.smsSofwareEng.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByPersonCode(Integer personCode);
}
