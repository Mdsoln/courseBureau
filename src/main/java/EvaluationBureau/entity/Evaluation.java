package EvaluationBureau.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @SequenceGenerator(
            name = "evaluation_sequence",
            sequenceName = "evaluation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "evaluation_sequence"
    )
    private Long evaluation_id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "particular_id", referencedColumnName = "particularsId")
    private Particulars particulars;//(foreign key referencing the Particulars table)

    private String evaluation_type;//course or instructor evaluation
    private LocalDate timestamp;//(date and time the evaluation was submitted)
    private String comments;//(optional: allows students to provide open-ended feedback)

}
