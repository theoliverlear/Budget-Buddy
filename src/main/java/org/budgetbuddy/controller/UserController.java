package org.budgetbuddy.controller;
//=================================-Imports-==================================
import jakarta.servlet.http.HttpSession;
import org.budgetbuddy.communication.request.UserRequest;
import org.budgetbuddy.communication.response.UserResponse;
import org.budgetbuddy.entity.user.SafePassword;
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    //============================-Variables-=================================
    @Autowired
    UserService userService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //--------------------------------User------------------------------------
    @RequestMapping("/")
    public String account() {
        // Return the user account page.
        return "user";
    }
    //-------------------------------Login------------------------------------
    @RequestMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest,
                                              HttpSession session) {
        // Check to see if the user exists in the database.
        boolean userExists = this.userService.userExists(userRequest.getUsername());
        // If the user exists, check to see if the password is correct.
        if (userExists) {
            User user = this.userService.getUserRepository().findByUsername(userRequest.getUsername());
            SafePassword safePassword = user.getSafePassword();
            // Compare the user's password with the request's password.
            boolean isAuthorized = safePassword.compareUnencodedPassword(userRequest.getPassword());
            // If the password is correct, set the user in the session.
            if (isAuthorized) {
                session.setAttribute("user", user);
            }
            // Return whether the user is authorized with a success status.
            return new ResponseEntity<>(new UserResponse(isAuthorized), HttpStatus.OK);
        } else {
            // Return that the user does not exist with a not found status.
            return new ResponseEntity<>(new UserResponse(false), HttpStatus.NOT_FOUND);
        }
    }
    //------------------------------Sign-Up-----------------------------------
    @RequestMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody UserRequest userRequest,
                                               HttpSession session) {
        // Check to see if the user already exists in the database.
        boolean userExists = this.userService.userExists(userRequest.getUsername());
        // If the user does not exist, create a new user and set them in the
        // session.
        if (!userExists) {
            SafePassword newSafePassword = new SafePassword(userRequest.getPassword());
            User newUser = new User(userRequest.getUsername(), newSafePassword);
            this.userService.getUserRepository().save(newUser);
            session.setAttribute("user", newUser);
            // Return that the user was created with a created status and
            // indicate that the user is authorized.
            return new ResponseEntity<>(new UserResponse(true), HttpStatus.CREATED);
        } else {
            // Otherwise, return that the user already exists in the database
            // with a conflict status.
            return new ResponseEntity<>(new UserResponse(false), HttpStatus.CONFLICT);
        }
    }
    //------------------------------Logout------------------------------------
    @RequestMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // Get the current user from the session.
        User currentUser = (User) session.getAttribute("user");
        // If the user is not logged in, return an unauthorized status.
        if (currentUser == null) {
            return new ResponseEntity<>("Not Logged In", HttpStatus.UNAUTHORIZED);
        } else {
            // Otherwise, remove the user from the session and return a
            // success status.
            session.removeAttribute("user");
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }
    //-----------------------------Logged-In----------------------------------
    @RequestMapping("/loggedin")
    public ResponseEntity<String> isLoggedIn(HttpSession session) {
        // Get the current user from the session.
        User currentUser = (User) session.getAttribute("user");
        // If the user is not logged in, return an unauthorized status since
        // the user is not logged in.
        if (currentUser == null) {
            return new ResponseEntity<>("Not Logged In", HttpStatus.UNAUTHORIZED);
        } else {
            // Otherwise, return an OK status since the user is logged in.
            return new ResponseEntity<>("Logged In", HttpStatus.OK);
        }
    }
    //--------------------------Current-Username------------------------------
    @RequestMapping("/current/username")
    public ResponseEntity<String> getCurrentUsername(HttpSession session) {
        // Get the current user from the session.
        User currentUser = (User) session.getAttribute("user");
        // If the user is not logged in, return an unauthorized status.
        if (currentUser == null) {
            return new ResponseEntity<>("Not Logged In", HttpStatus.UNAUTHORIZED);
        } else {
            // Otherwise, return the current user's username with an "@"
            // symbol prepended to it.
            return new ResponseEntity<>("@" + currentUser.getUsername(), HttpStatus.OK);
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
