package EvaluationBureau.jpa;

import EvaluationBureau.entity.CourseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEvaluationRepo extends JpaRepository<CourseEvaluation,Long> {

}
