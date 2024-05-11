package EvaluationBureau.service;

import EvaluationBureau.constants.BureauRoles;
import EvaluationBureau.constants.EmailException;
import EvaluationBureau.entity.User;
import EvaluationBureau.jpa.UserRepository;
import EvaluationBureau.models.UserModel;
import EvaluationBureau.service.inter.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements BaseService {

    private final UserRepository repository;


    @Override
    public ResponseEntity<String> registerUser(UserModel payload) {
        User existsUser = repository.findByEmail(payload.getEmail());
        if (existsUser != null){
            throw new EmailException("Already taken");
        }

        User user = new User();
        user.setEmail(payload.getEmail());
        user.setUserRegNo(payload.getRegNo());
        user.setMobileNumber(payload.getMobile());
        user.setPassword(payload.getPassword());
        if (payload.getRegNo().startsWith("20") || payload.getRegNo().contains("-04-")){
            user.setUserRoles(BureauRoles.STUDENT);
        } else if (payload.getEmail().contains("udsm@gmail.com")) {
            user.setUserRoles(BureauRoles.INSTRUCTOR);
        }
        repository.save(user);

        return ResponseEntity.ok("Registered successfully");
    }
}
