package EvaluationBureau.jpa;

import EvaluationBureau.entity.InstructorEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorEvaluationRepo extends JpaRepository<InstructorEvaluation,Long> {

}
