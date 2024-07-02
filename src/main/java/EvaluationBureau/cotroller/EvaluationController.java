package EvaluationBureau.cotroller;


import EvaluationBureau.constants.HandleExceptions;
import EvaluationBureau.constants.NotFoundExceptions;
import EvaluationBureau.models.PageResponse;
import EvaluationBureau.service.EvaluationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping(path = "/evaluation")
@RequiredArgsConstructor
@CrossOrigin
public class EvaluationController {

    private final EvaluationServiceImpl evaluationService;

    @CrossOrigin()
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

    @CrossOrigin()
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


    @CrossOrigin()
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

    @CrossOrigin()
    @GetMapping("/particulars")
    public ResponseEntity<PageResponse<Object[]>> getParticulars(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ){
        try {
            if (pageNumber < 0 || pageSize <= 0){
                throw new HandleExceptions("Invalid page number or size");
            }

            Pageable pages = PageRequest.of(pageNumber,pageSize, Sort.by("courseCode"));
            Page<Object[]> response = evaluationService.getParticulars(pages);

            PageResponse<Object[]> pageResponse = new PageResponse<>(
                    response.getContent(),
                    response.getTotalPages(),
                    response.getNumberOfElements()
            );

            return new ResponseEntity<>(pageResponse, HttpStatus.OK);

        }catch (NotFoundExceptions | HandleExceptions handler){
            throw new HandleExceptions("Error: "+handler.getMessage());
        }
    }
}
