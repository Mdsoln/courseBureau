package EvaluationBureau.service.inter;

import EvaluationBureau.models.UserModel;
import org.springframework.http.ResponseEntity;

public interface BaseService {
    ResponseEntity<String> registerUser(UserModel payload);

}
