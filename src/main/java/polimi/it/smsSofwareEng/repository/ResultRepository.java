package polimi.it.smsSofwareEng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polimi.it.smsSofwareEng.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {

}
