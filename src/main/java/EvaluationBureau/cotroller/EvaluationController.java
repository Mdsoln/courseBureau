package EvaluationBureau.cotroller;


import EvaluationBureau.service.EvaluationServiceImpl;
import EvaluationBureau.service.inter.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/evaluation")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationServiceImpl evaluationService;

    @PostMapping("/particulars")
    public ResponseEntity<String> particulars(
       @RequestParam(name = "courseCode", required = false) String courseCode,
       @RequestParam(name = "courseTitle", required = false) String courseTitle,
       @RequestParam(name = "instructorName", required = false) String instructorName,
       @RequestParam(name = "lectureVenue", required = false) String lectureVenue,
       @RequestParam(name = "classSize", required = false) int classSize,
       @RequestParam(name = "department", required = false) String department,
       @RequestParam(name = "courseCollege", required = false) String courseCollege,
       @RequestParam(name = "studentProgramme", required = false) String studentProgramme,
       @RequestParam(name = "studyYear", required = false) String studyYear,
       @RequestParam(name = "courseSemester", required = false) String courseSemester
    ){

        return evaluationService.saveParticulars(courseCode,courseTitle,instructorName,lectureVenue,
                classSize,department,courseCollege,studentProgramme,studyYear,courseSemester);
    }

    @PostMapping("/instructor-evaluation")
    public ResponseEntity<String> instructorEvaluation(
            @RequestParam(name = "courseCode", required = false) String courseCode,
            @RequestParam(name = "preparation", required = false) String preparation,
            @RequestParam(name = "possession", required = false) String possession,
            @RequestParam(name = "deliveryMode", required = false) String deliveryMode,
            @RequestParam(name = "timeManagement", required = false) String timeManagement,
            @RequestParam(name = "fairnessGrading", required = false) String fairnessGrading,
            @RequestParam(name = "feedbackAssignment", required = false) String feedbackAssignment,
            @RequestParam(name = "instructorAttendance", required = false) String instructorAttendance,
            @RequestParam(name = "consultationAvailability", required = false) String consultationAvailability,
            @RequestParam(name = "interactWithStudent", required = false) String interactWithStudent,
            @RequestParam(name = "competencyRate", required = false) String competencyRate,
            @RequestParam(name = "sexualHarassmentCode", required = false) String sexualHarassmentCode
    ){

        return evaluationService.saveInstructorEvaluation(
                courseCode,preparation,possession,deliveryMode,timeManagement,fairnessGrading,feedbackAssignment,
                instructorAttendance,consultationAvailability,interactWithStudent,competencyRate,sexualHarassmentCode
        );
    }


    @PostMapping("/course-evaluation")
    public ResponseEntity<String> courseEvaluation(
          @RequestParam(name = "courseCode", required = false)  String courseCode,
          @RequestParam(name = "courseObjective", required = false)  String courseObjective,
          @RequestParam(name = "contentCoverage", required = false)  String contentCoverage,
          @RequestParam(name = "assessmentMode", required = false)  String assessmentMode,
          @RequestParam(name = "teachingMethods", required = false)  String teachingMethods,
          @RequestParam(name = "updateLectureNotes", required = false)  String updateLectureNotes,
          @RequestParam(name = "linkTheoryPractise", required = false)  String linkTheoryPractise,
          @RequestParam(name = "seminarsTutorials", required = false)  String seminarsTutorials,
          @RequestParam(name = "courseRelevance", required = false)  String courseRelevance
    ){
        return evaluationService.saveCourseEvaluation(courseCode,courseObjective,contentCoverage,assessmentMode,
                teachingMethods,updateLectureNotes,linkTheoryPractise,seminarsTutorials,courseRelevance);
    }
}
