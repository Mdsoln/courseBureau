package EvaluationBureau.service.inter;


import org.springframework.http.ResponseEntity;

public interface EvaluationService {

    ResponseEntity<String> saveParticulars(String courseCode,String courseTitle,String instructorName,String lectureVenue,
                                           int classSize,String department,String courseCollege,String studentProgramme,String studyYear,String courseSemester);

    ResponseEntity<String> saveInstructorEvaluation(String courseCode, String preparation, String possession, String deliveryMode, String timeManagement, String fairnessGrading,String instructorAttendance, String feedbackAssignment, String consultationAvailability, String interactWithStudent, String competencyRate, String sexualHarassmentCode);

    ResponseEntity<String> saveCourseEvaluation(String courseCode, String courseObjective, String contentCoverage, String assessmentMode, String teachingMethods, String updateLectureNotes, String linkTheoryPractise, String seminarsTutorials, String courseRelevance);
}
