package org.budgetbuddy.controller;
//=================================-Imports-==================================
import org.budgetbuddy.communication.request.AccountRequest;
import org.budgetbuddy.communication.response.AccountResponse;
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
        return "account";
    }
    //-------------------------------Login------------------------------------
    @RequestMapping("/login")
    public ResponseEntity<AccountResponse> login(@ModelAttribute AccountRequest accountRequest) {
        boolean userExists = this.userService.userExists(accountRequest.getUsername());
        if (userExists) {
            User user = this.userService.getUserRepository().findByUsername(accountRequest.getUsername());
            SafePassword safePassword = user.getSafePassword();
            boolean isAuthorized = safePassword.compareUnencodedPassword(accountRequest.getPassword());
            return new ResponseEntity<>(new AccountResponse(isAuthorized), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AccountResponse(false), HttpStatus.NOT_FOUND);
        }
    }
    //------------------------------Sign-Up-----------------------------------
    @RequestMapping("/signup")
    public ResponseEntity<AccountResponse> signup(@ModelAttribute AccountRequest accountRequest) {
        boolean userExists = this.userService.userExists(accountRequest.getUsername());
        if (!userExists) {
            SafePassword newSafePassword = new SafePassword(accountRequest.getPassword());
            User newUser = new User(accountRequest.getUsername(), newSafePassword);
            this.userService.getUserRepository().save(newUser);
            return new ResponseEntity<>(new AccountResponse(true), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new AccountResponse(false), HttpStatus.CONFLICT);
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
