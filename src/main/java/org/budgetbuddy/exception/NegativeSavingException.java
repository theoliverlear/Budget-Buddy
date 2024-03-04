package org.budgetbuddy.exception;
//=================================-Imports-==================================
public class NegativeSavingException extends Exception {
    //============================-Variables-=================================
    // Create a new exception for when the user tries to save a negative
    // amount of money.
    //===========================-Constructors-===============================
    public NegativeSavingException() {
        super("Savings cannot be negative.");
    }
    public NegativeSavingException(String message) {
        super(message);
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
