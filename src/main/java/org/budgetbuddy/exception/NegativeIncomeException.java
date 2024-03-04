package org.budgetbuddy.exception;
//=================================-Imports-==================================
public class NegativeIncomeException extends Exception {
    //============================-Variables-=================================
    // If someone inputs a negative number for their income, this exception
    // will be thrown.
    //===========================-Constructors-===============================
    public NegativeIncomeException() {
        super("Income cannot be negative.");
    }
    public NegativeIncomeException(String message) {
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
