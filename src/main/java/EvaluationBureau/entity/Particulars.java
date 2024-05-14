package EvaluationBureau.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "particulars")
public class Particulars {
    @Id
    @SequenceGenerator(
            name = "particular_sequence",
            sequenceName = "particular_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "particular_sequence"
    )
    private Long particularsId;

    @OneToMany(mappedBy = "particulars", cascade = CascadeType.PERSIST)
    private List<InstructorEvaluation> instructorEvaluation;

    @OneToMany(mappedBy = "particulars", cascade = CascadeType.PERSIST)
    private List<CourseEvaluation> courseEvaluation;

    @OneToOne(mappedBy = "particulars", cascade = CascadeType.PERSIST)
    private Evaluation evaluation;

    private String courseCode;
    private String courseTitle;

    @Column(name = "instructor", nullable = false)
    private String instructorName;

    @Column(name = "venue", nullable = false)
    private String lectureVenue;// DO1, B302 etc

    private int classSize;// total number of students in the class
    private String department;

    @Column(name = "college", nullable = false)
    private String courseCollege;//college/school/institute

    private LocalDate timestamp;//time of filling the form
    private String studentProgramme;// a student needs to specify which programme he/she is taking
    private String studyYear;//     I, II, III, IV
    private String courseSemester; // First, Second

    @PrePersist
    public void onCreate(){
        this.timestamp = LocalDate.now();
    }
}
