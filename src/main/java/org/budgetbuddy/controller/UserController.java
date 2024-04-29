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

/**
 * <h6>This class is the controller for the User.</h6>
 *
 * It handles the User's login, sign-up, logout, and logged-in status.
 * @see org.budgetbuddy.entity.user.User
 * @see org.budgetbuddy.service.UserService
 * @see org.budgetbuddy.communication.request.UserRequest
 * @see org.budgetbuddy.communication.response.UserResponse
 */
@Controller
@RequestMapping("/user")
public class UserController {
    //============================-Variables-=================================
    @Autowired
    UserService userService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //--------------------------------User------------------------------------
    /**
     * <h6>This method returns the user account page.</h6>
     *
     * @return The user account page.
     */
    @RequestMapping("/")
    public String account() {
        // Return the user account page.
        return "user";
    }
    //-------------------------------Login------------------------------------
    /**
     * <h6>This method logs the User in.</h6>
     *
     * @param userRequest The UserRequest object containing the User's
     *                    username and password.
     * @param session HttpSession object representing the session of the user.
     * @return A ResponseEntity with the UserResponse and the HTTP status.
     * <ul>
     *     <li>If the user exists and the password is correct, return a
     *         ResponseEntity with a UserResponse indicating that the User is
     *         authorized and an OK status.</li>
     *     <li>If the user does not exist, return a ResponseEntity with a
     *         UserResponse indicating that the User is not authorized and a
     *         (HTTP.Status.NOT_FOUND) status.</li>
     * </ul>
     */
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
    /**
     * <h6>This method signs the User up.</h6>
     *
     * @param userRequest The UserRequest object containing the User's
     *                    username and password.
     * @param session HttpSession object representing the session of the user.
     * @return A ResponseEntity with the UserResponse and the HTTP status.
     * <ul>
     *     <li>If the user does not exist, create a new User and set them in
     *         the session. Return a ResponseEntity with a UserResponse
     *         indicating that the User was created and an OK status.</li>
     *     <li>If the User already exists, return a ResponseEntity with a
     *         UserResponse indicating that the User already exists and a
     *         (HTTP.Status.CONFLICT) status.</li>
     * </ul>
     */
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
    /**
     * <h6>This method logs the User out.</h6>
     *
     * @param session HttpSession object representing the session of the user.
     * @return A ResponseEntity with a message and the HTTP status.
     * <ul>
     *     <li>If the user is not logged in, return a ResponseEntity with a
     *         message indicating that the User is not logged in and an
     *         (HTTP.Status.UNAUTHORIZED) status.</li>
     *     <li>If the User is logged in, remove the User from the session and
     *         return a ResponseEntity with a message indicating that the User
     *         was logged out and an OK status.</li>
     * </ul>
     */
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
    /**
     * <h6>This method checks if the User is logged in.</h6>
     *
     * @param session HttpSession object representing the session of the user.
     * @return A ResponseEntity with a message and the HTTP status.
     * <ul>
     *     <li>If the user is not logged in, return a ResponseEntity with a
     *         message indicating that the User is not logged in and an
     *         (HTTP.Status.UNAUTHORIZED) status.</li>
     *     <li>If the User is logged in, return a ResponseEntity with a
     *         message indicating that the User is logged in and an OK status.
     *         </li>
     * </ul>
     */
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
    /**
     * <h6>This method returns the current User's username.</h6>
     *
     * @param session HttpSession object representing the session of the user.
     * @return A ResponseEntity with the current User's username and the HTTP
     * status.
     * <ul>
     *     <li>If the user is not logged in, return a ResponseEntity with a
     *         message indicating that the User is not logged in and an
     *         (HTTP.Status.UNAUTHORIZED) status.</li>
     *     <li>If the User is logged in, return a ResponseEntity with the
     *         current User's username with an "@" symbol prepended to it and
     *         an OK status.</li>
     * </ul>
     */
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
