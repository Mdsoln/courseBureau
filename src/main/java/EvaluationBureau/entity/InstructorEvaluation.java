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
@Table(name = "instructor_evaluation")
public class InstructorEvaluation {
    @Id
    @SequenceGenerator(
            name = "instructor_sequence",
            sequenceName = "instructor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructor_sequence"
    )
    private Long instructorEvaluationId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "particular_id", referencedColumnName = "particularsId")
    private Particulars particulars;

    private String preparation;//instructor's preparedness on the subject
    private String possession;//instructor's possession on up-to date skills and knowledge in the subject
    private String deliveryMode;// instructor's mode of delivery of the subject (techniques and styles)
    private String timeManagement;//instructor's management of time during teaching
    private String fairnessGrading;//instructor's fairness in grading of assignment and tests against making scheme
    private String feedbackAssignment;//instructor's capacity to provide timely feedback on assignment and tests(with 2 weeks)
    private String instructorAttendance;// instructor's attendance in the class;
    private String consultationAvailability;//instructor's availability for consultation
    private String interactWithStudent;//way the instructor interacts with students in the class
    private String competencyRate;// how do you rate the competency of the instructor to meet your learning satisfaction
    private String sexualHarassmentCode;// Instructor observed or compiled with UDSM Sexual Harassment Code? for No, there should be extra answers

    private LocalDateTime timestamp;//time of filling instructor evaluation form

    @PrePersist
    public void onCreate(){
        this.timestamp = LocalDateTime.now().withNano(0);
    }
}
