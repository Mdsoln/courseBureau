package EvaluationBureau.cotroller;

import EvaluationBureau.constants.EmailException;
import EvaluationBureau.models.AuthResponse;
import EvaluationBureau.models.LoginRequest;
import EvaluationBureau.models.UserModel;
import EvaluationBureau.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin()
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @CrossOrigin()
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel payload){
        return userService.registerUser(payload);
    }

    @CrossOrigin()
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
       return userService.signIn(request);
    }


    @CrossOrigin()
    @PostMapping("/edit-email")
    public ResponseEntity<String> editEmail(
            @RequestParam(name = "oldEmail", required = false) String oldEmail,
            @RequestParam(name = "newEmail", required = false) String newEmail
    ){
        try {

            if (oldEmail == null && newEmail == null){
                throw new EmailException("Required both old and new email");
            }

           String message = userService.editEmail(oldEmail, newEmail);
            if (message.contains("updated")){
                return ResponseEntity.ok("Successfully email update");
            }else {
                return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (EmailException exception){
            throw new EmailException("Error: "+exception.getMessage());
        }
    }

    @CrossOrigin()
    @PostMapping("/edit-mobile")
    public ResponseEntity<String> editMobile(
            @RequestParam(name = "oldMobile", required = false) String oldMobile,
            @RequestParam(name = "newMobile", required = false) String newMobile
    ){
        try {

            if (oldMobile == null && newMobile == null){
                throw new EmailException("Required both old and new email");
            }

            String message = userService.editPassword(oldMobile, newMobile);
            if (message.contains("updated")){
                return ResponseEntity.ok("Successfully phone number update");
            }else {
                return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (EmailException exception){
            throw new EmailException("Error: "+exception.getMessage());
        }
    }
}
