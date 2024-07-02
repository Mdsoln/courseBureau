package EvaluationBureau.jpa;

import EvaluationBureau.entity.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {

    @Query("SELECT p.courseCode, p.courseTitle, p.instructorName, p.lectureVenue, p.classSize, p.department, p.courseCollege, p.timestamp, p.studentProgramme, p.studyYear, p.courseSemester " +
            "FROM Particulars p "
          )
    Page<Object[]> findParticulars(Pageable pages);
}
