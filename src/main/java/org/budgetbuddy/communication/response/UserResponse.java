package org.budgetbuddy.communication.response;
//=================================-Imports-==================================
public class UserResponse {
    //============================-Variables-=================================
    boolean isAuthorized;
    //===========================-Constructors-===============================
    public UserResponse() {
        this.isAuthorized = false;
    }
    public UserResponse(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================
    public boolean getIsAuthorized() {
        return this.isAuthorized;
    }
    //=============================-Setters-==================================
    public void setIsAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }
}
