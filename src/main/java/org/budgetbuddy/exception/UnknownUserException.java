package org.budgetbuddy.exception;
//=================================-Imports-==================================
public class UnknownUserException extends Exception {
    //============================-Variables-=================================
    // This exception is thrown when a user is not found in the database.
    //=============================-Methods-==================================

    //===========================-Constructors-===============================
    public UnknownUserException() {
        super("User not found.");
    }
    public UnknownUserException(String message) {
        super(message);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
