package EvaluationBureau.entity;

import EvaluationBureau.constants.BureauRoles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;

    @Column(name = "reg_no", nullable = false)
    private String userRegNo;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private BureauRoles userRoles;

    private LocalDate timestamp;//registered date and time

    @PrePersist
    public void onCreate(){
        this.timestamp = LocalDate.now();
    }
}
