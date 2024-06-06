package EvaluationBureau.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "course_evaluation")
public class CourseEvaluation {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseEvaluationId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "particular_id", referencedColumnName = "particularsId")
    private Particulars particulars;

    private String courseObjective;//how clear was the objective of the course
    private String contentCoverage;//how well the course content coverage
    private String assessmentMode;//how well was the mode of assessment, e.g. sufficient test, assignment, timed essays
    private String teachingMethods;//class participation, demonstration, etc
    private String updateLectureNotes;//how well(updated) were the lecture note and handouts
    private String linkTheoryPractise;//how well did the course link theory and practise
    private String seminarsTutorials;//how adequate were the tutorials, seminars or practicals
    private String courseRelevance;//how do you rate the relevance of the course to meet your expectations

    private LocalDateTime timestamp;//time of filling course evaluation form

    @PrePersist
    public void onCreate(){
        this.timestamp = LocalDateTime.now().withNano(0);
    }
}
