package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.communication.request.UserRequest;
import org.budgetbuddy.communication.response.UserResponse;
import org.budgetbuddy.entity.user.SafePassword;
import org.budgetbuddy.entity.user.User;
import org.budgetbuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    //============================-Variables-=================================
    @Autowired
    UserService userService;
    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //------------------------------Account-----------------------------------
    @RequestMapping("/")
    public String account() {
        return "user";
    }
    //-------------------------------Login------------------------------------
    @RequestMapping("/login")
    public ResponseEntity<UserResponse> login(@ModelAttribute UserRequest userRequest) {
        boolean userExists = this.userService.userExists(userRequest.getUsername());
        if (userExists) {
            User user = this.userService.getUserRepository().findByUsername(userRequest.getUsername());
            SafePassword safePassword = user.getSafePassword();
            boolean isAuthorized = safePassword.compareUnencodedPassword(userRequest.getPassword());
            return new ResponseEntity<>(new UserResponse(isAuthorized), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UserResponse(false), HttpStatus.NOT_FOUND);
        }
    }
    //------------------------------Sign-Up-----------------------------------
    @RequestMapping("/signup")
    public ResponseEntity<UserResponse> signup(@ModelAttribute UserRequest userRequest) {
        boolean userExists = this.userService.userExists(userRequest.getUsername());
        if (!userExists) {
            SafePassword newSafePassword = new SafePassword(userRequest.getPassword());
            User newUser = new User(userRequest.getUsername(), newSafePassword);
            this.userService.getUserRepository().save(newUser);
            return new ResponseEntity<>(new UserResponse(true), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new UserResponse(false), HttpStatus.CONFLICT);
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
