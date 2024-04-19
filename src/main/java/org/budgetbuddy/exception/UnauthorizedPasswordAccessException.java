package org.budgetbuddy.exception;

public class UnauthorizedPasswordAccessException extends RuntimeException {
    //============================-Variables-=================================
    // If someone tries to access a password that they are not authorized to
    // access, this exception will be thrown.
    public static final String EXCEPTION_MESSAGE = "Unauthorized access to " +
                                                   "password. No disclosure " +
                                                   "has been made.";
    //===========================-Constructors-===============================
    public UnauthorizedPasswordAccessException() {
        super(EXCEPTION_MESSAGE);
    }
    public UnauthorizedPasswordAccessException(String message) {
        super(message);
    }
}
