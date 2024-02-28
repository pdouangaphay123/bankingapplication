package Controller;

import CustomException.EmailExistsInDbException;
import CustomException.InvalidEmailPasswordException;

import Model.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin (origins = {"https://localhost:8080"},
allowedHeaders = "*", exposedHeaders = "*", allowCredentials = "true", maxAge = 244444)
public class AuthController {

    final UserService userService;

    @Autowired
    public AuthController(UserService userService){ this.userService = userService;}
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            User registeredUser = userService.createUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (EmailExistsInDbException | InvalidEmailPasswordException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
