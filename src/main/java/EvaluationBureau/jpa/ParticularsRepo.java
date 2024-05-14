package EvaluationBureau.jpa;

import EvaluationBureau.entity.Particulars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticularsRepo extends JpaRepository<Particulars,Long> {

}
