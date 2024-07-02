package EvaluationBureau.service;

import EvaluationBureau.constants.BureauRoles;
import EvaluationBureau.constants.EmailException;
import EvaluationBureau.entity.User;
import EvaluationBureau.jpa.UserRepository;
import EvaluationBureau.jwt.JwtService;
import EvaluationBureau.models.AuthResponse;
import EvaluationBureau.models.LoginRequest;
import EvaluationBureau.models.UserModel;
import EvaluationBureau.service.inter.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements BaseService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<String> registerUser(UserModel payload) {
        User existsUser = repository.findByEmail(payload.getEmail());
        if (existsUser != null){
            throw new EmailException("Already taken");
        }

        User user = new User();
        user.setEmail(payload.getEmail());
        user.setRegistration(payload.getRegNo());
        user.setMobileNumber(payload.getMobile());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        if (payload.getRegNo().startsWith("20") || payload.getRegNo().contains("-04-")){
            user.setUserRoles(BureauRoles.STUDENT);
        } else if (payload.getEmail().contains("udsm@gmail.com")) {
            user.setUserRoles(BureauRoles.INSTRUCTOR);
        }
        repository.save(user);

        return ResponseEntity.ok("Registered successfully");
    }

    @Override
    public ResponseEntity<AuthResponse> signIn(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
//                            request.getRegNo(),
                            request.getPassword()
                    )
            );
            var user = repository.findByRegistration(request.getUsername());
            var token = jwtService.generateToken(user);
            return ResponseEntity.ok(AuthResponse.builder().token(token).build());
        }catch (AuthenticationException handleExceptions){
            AuthResponse error = AuthResponse.builder()
                    .token("Error: "+handleExceptions.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @Override
    public String editEmail(String oldEmail, String newEmail) {
        try {
            User user = repository.findByEmail(oldEmail);
            if (user == null){
                throw new EmailException("Oops! email mismatches with existing");
            }
            user.setEmail(newEmail);
            repository.save(user);

            return "email has been updated successfully";
        }catch (EmailException exception){
            throw new EmailException(exception.getMessage());
        }
    }

    @Override
    public String editPassword(String oldMobile, String newMobile) {
        try {
            User user = repository.MobileNumber(oldMobile);
            if (user == null){
                throw new EmailException("Oops! email mismatches with existing");
            }
            user.setEmail(newMobile);
            repository.save(user);

            return "email has been updated successfully";
        }catch (EmailException exception){
            throw new EmailException(exception.getMessage());
        }
    }
}
//todo: edit password for student
//todo: report generation evaluation analysis ie instructor and course analysis