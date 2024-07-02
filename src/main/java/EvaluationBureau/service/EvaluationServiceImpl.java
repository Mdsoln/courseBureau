package EvaluationBureau.service;

import EvaluationBureau.constants.EvaluationConfig;
import EvaluationBureau.constants.HandleExceptions;
import EvaluationBureau.constants.NotFoundExceptions;
import EvaluationBureau.entity.CourseEvaluation;
import EvaluationBureau.entity.Evaluation;
import EvaluationBureau.entity.InstructorEvaluation;
import EvaluationBureau.entity.Particulars;
import EvaluationBureau.jpa.CourseEvaluationRepo;
import EvaluationBureau.jpa.EvaluationRepo;
import EvaluationBureau.jpa.InstructorEvaluationRepo;
import EvaluationBureau.jpa.ParticularsRepo;
import EvaluationBureau.service.inter.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final ParticularsRepo particularsRepo;
    private final InstructorEvaluationRepo instructorEvaluationRepo;
    private final EvaluationRepo evaluationRepo;
    private final CourseEvaluationRepo courseEvaluationRepo;


    @Override
    public ResponseEntity<String> saveParticulars(
            String courseCode, String courseTitle, String instructorName,
            String lectureVenue, int classSize, String department,
            String courseCollege, String studentProgramme, String studyYear, String courseSemester)
    {
        Particulars particulars = Particulars.builder()
                .courseCode(courseCode)
                .courseTitle(courseTitle)
                .instructorName(instructorName)
                .lectureVenue(lectureVenue)
                .classSize(classSize)
                .department(department)
                .courseCollege(courseCollege)
                .studentProgramme(studentProgramme)
                .studyYear(studyYear)
                .courseSemester(courseSemester)
                .build();
        particularsRepo.save(particulars);

        return ResponseEntity.ok("Particulars saved successfully");
    }

    @Override
    public ResponseEntity<String> saveInstructorEvaluation(
            String courseCode, String preparation, String possession, String deliveryMode,
            String timeManagement, String fairnessGrading, String feedbackAssignment,
            String instructorAttendance, String consultationAvailability, String interactWithStudent,
            String competencyRate, String sexualHarassmentCode)
    {
        try {
            Particulars particulars = particularsRepo.findByCourseCode(courseCode);
            if (particulars == null){
                throw new NotFoundExceptions("Oops! Particulars not found");
            }

            InstructorEvaluation instructorEvaluation = new InstructorEvaluation();
            instructorEvaluation.setPreparation(preparation);
            instructorEvaluation.setPossession(possession);
            instructorEvaluation.setDeliveryMode(deliveryMode);
            instructorEvaluation.setTimeManagement(timeManagement);
            instructorEvaluation.setFairnessGrading(fairnessGrading);
            instructorEvaluation.setFeedbackAssignment(feedbackAssignment);
            instructorEvaluation.setInstructorAttendance(instructorAttendance);
            instructorEvaluation.setConsultationAvailability(consultationAvailability);
            instructorEvaluation.setInteractWithStudent(interactWithStudent);
            instructorEvaluation.setCompetencyRate(competencyRate);
            if (sexualHarassmentCode.equals("yes")) {
                instructorEvaluation.setSexualHarassmentCode(EvaluationConfig.SEXUAL_HARASSMENT_CODE);
            } else{
                instructorEvaluation.setSexualHarassmentCode(sexualHarassmentCode);
            }
            instructorEvaluation.setParticulars(particulars);

            instructorEvaluationRepo.save(instructorEvaluation);

             Evaluation evaluation = Evaluation.builder()
                     .evaluation_type(EvaluationConfig.INSTRUCTOR_EVALUATION)
                     .particulars(particulars)
                     .comments(EvaluationConfig.COMMENT)
                     .build();
             evaluationRepo.save(evaluation);

            return ResponseEntity.ok("Saved instructor evaluations");
        }catch (NotFoundExceptions notFoundExceptions){
            throw new NotFoundExceptions(notFoundExceptions.getMessage());
        }

    }

    @Override
    public ResponseEntity<String> saveCourseEvaluation(
            String courseCode, String courseObjective, String contentCoverage,
            String assessmentMode, String teachingMethods, String updateLectureNotes,
            String linkTheoryPractise, String seminarsTutorials, String courseRelevance)
    {
        try {
            Particulars particulars = particularsRepo.findByCourseCode(courseCode);
            if (particulars == null){
                throw new NotFoundExceptions("Oops! required  particulars");
            }

            CourseEvaluation courseEvaluation = CourseEvaluation.builder()
                    .courseObjective(courseObjective)
                    .contentCoverage(contentCoverage)
                    .assessmentMode(assessmentMode)
                    .teachingMethods(teachingMethods)
                    .updateLectureNotes(updateLectureNotes)
                    .linkTheoryPractise(linkTheoryPractise)
                    .seminarsTutorials(seminarsTutorials)
                    .courseRelevance(courseRelevance)
                    .particulars(particulars)
                    .build();
            courseEvaluationRepo.save(courseEvaluation);

            Evaluation evaluation = Evaluation.builder()
                    .particulars(particulars)
                    .evaluation_type(EvaluationConfig.COURSE_EVALUATION)
                    .comments(EvaluationConfig.COMMENT)
                    .build();
            evaluationRepo.save(evaluation);

        return ResponseEntity.ok("Saved course evaluation");
        }catch (NotFoundExceptions notFoundExceptions){
            throw new NotFoundExceptions(notFoundExceptions.getMessage());
        }

    }

    @Override
    public Page<Object[]> getParticulars(Pageable pages) {
        try {
            return evaluationRepo.findParticulars(pages);
        }catch (DataAccessException exception){
            throw new HandleExceptions(exception.getMessage());
        }
    }
}
