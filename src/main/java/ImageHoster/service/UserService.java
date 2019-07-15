package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Pattern pattern;

    private Matcher matcher;

    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }
    private static final String PASSWORD_PATTERN =
            "(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{4,}";


    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    public boolean validatePassword(String passwordToBeValidated) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwordToBeValidated);
        return matcher.matches();
    }
}
