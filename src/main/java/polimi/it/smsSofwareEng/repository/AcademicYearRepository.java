package polimi.it.smsSofwareEng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polimi.it.smsSofwareEng.model.AcademicYear;

import javax.transaction.Transactional;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear,Long> {

    AcademicYear findAcademicYearByYearTitle(String yearTitle);
}
