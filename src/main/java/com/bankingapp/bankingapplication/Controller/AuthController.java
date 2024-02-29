package com.bankingapp.bankingapplication.Controller;

import com.bankingapp.bankingapplication.CustomException.EmailExistsInDbException;
import com.bankingapp.bankingapplication.CustomException.InvalidEmailPasswordException;

import com.bankingapp.bankingapplication.DTO.LoginCreds;
import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin (origins = {"https://localhost:8080"},
allowedHeaders = "*", exposedHeaders = "*", allowCredentials = "true", maxAge = 244444)
public class AuthController {

    final AuthService authService;

    private User sessionUser;

    public int getSessionUserId(){
        return sessionUser.getUserId();
    }
    @Autowired
    public AuthController(AuthService authService){ this.authService = authService;}

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            User registeredUser = authService.createUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (EmailExistsInDbException | InvalidEmailPasswordException e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> UserLogin(@RequestBody LoginCreds loginCreds){
        try {
            User loginUser = authService.loginUser(loginCreds);
            if (loginUser != null){
                sessionUser = loginUser;
                return ResponseEntity.ok(loginUser);
            }
            return ResponseEntity.badRequest().body("user not logged in");
        } catch (InvalidEmailPasswordException e){
            return ResponseEntity.badRequest().body(e);
        }
    }


}
