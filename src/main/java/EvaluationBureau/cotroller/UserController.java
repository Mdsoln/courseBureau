package EvaluationBureau.cotroller;

import EvaluationBureau.models.AuthResponse;
import EvaluationBureau.models.LoginRequest;
import EvaluationBureau.models.UserModel;
import EvaluationBureau.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel payload){
        return userService.registerUser(payload);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
       return userService.signIn(request);
    }
}
