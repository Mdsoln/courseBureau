package EvaluationBureau.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
//    private String regNo;
    private String password;
}
