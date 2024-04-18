package org.budgetbuddy.communication.response;
//=================================-Imports-==================================
public class AccountResponse {
    //============================-Variables-=================================
    boolean isAuthorized;
    //===========================-Constructors-===============================
    public AccountResponse() {
        this.isAuthorized = false;
    }
    public AccountResponse(boolean isAuthorized) {
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
