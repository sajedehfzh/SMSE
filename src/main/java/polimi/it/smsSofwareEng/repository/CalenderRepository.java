package polimi.it.smsSofwareEng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polimi.it.smsSofwareEng.model.AcademicCall;
@Repository
public interface CalenderRepository extends JpaRepository<AcademicCall,Long> {
}
