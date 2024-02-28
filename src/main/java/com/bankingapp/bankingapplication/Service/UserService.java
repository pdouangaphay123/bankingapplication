package com.bankingapp.bankingapplication.Service;

import com.bankingapp.bankingapplication.CustomException.EmailExistsInDbException;
import com.bankingapp.bankingapplication.CustomException.InvalidEmailPasswordException;
import com.bankingapp.bankingapplication.DTO.LoginCreds;
import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User createUser(User user) {
        if (user.getEmail() != null && user.getPassword() != null &&
                validateEmail(user.getEmail()) && validatePassword(user.getPassword())) {
            if (!user.getEmail().equals(userRepository.findByEmail(user.getEmail()))) {
                return userRepository.save(user);
            }
            else throw new EmailExistsInDbException("coolio");
        }
        throw new InvalidEmailPasswordException("hi mom");
    }

    public User loginUser(LoginCreds loginCreds){
            User loggedUser = userRepository.findByEmail(loginCreds.getEmail());
            if (loggedUser != null && Objects.equals(loggedUser.getPassword(), loginCreds.getPassword())){
                return loggedUser;
            }
            throw new InvalidEmailPasswordException("yeet");
    }


        /* VALIDATION EMAIL AND PW REQUIREMENTS*/
        // email validation regex pattern accepts from 6 to 16 email prefix chars A-z,a-z,0-9,_,. only
        // after the @ symbol accepts from range 2 to 8 chars a-z only, and after . accepts only a-z chars range 2 to 6.
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Za-z0-9._]{6,16}@[a-z]{2,8}.[a-z]{2,6}$", Pattern.CASE_INSENSITIVE);

        public static boolean validateEmailId (String emailId){
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailId);
            return matcher.find();
        }
        public static boolean validateEmail (String email){

            if (email == null || !validateEmailId(email)) { // validates for if null email
                return false;
            }
            return true;
        }

        public static boolean validatePassword (String password){

            if (password == null) { // validates for if null password
                //context.status(400);
                //context.json("Password cannot be empty!");
                return false;
            }

            if (password.length() < 5) { // password must be at least 5 characters
                //context.status(400);
                //context.json("Password must be at least 5 characters!");
                return false;
            } else if (!Pattern.matches("[^ ]*", password)) { // password cannot contain space
                //context.status(400);
                //context.json("Password cannot contain space!");
                return false;
            } else if (!password.matches(".*\\d.*")) { // password must have at least 1 digit
                //context.status(400);
                //context.json("Password must have at least 1 digit!");
                return false;
            }

            return true;
        }
    }

