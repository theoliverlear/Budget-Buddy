package org.budgetbuddy.model.format;
//=================================-Imports-==================================
public class FormattedCurrency {
    //============================-Variables-=================================
    double amount;
    double formattedAmount;
    //===========================-Constructors-===============================
    public FormattedCurrency(double amount) {
        this.amount = amount;
        this.formattedAmount = this.formatCurrency(amount);
    }
    //=============================-Methods-==================================

    //--------------------------Format-Currency-------------------------------
    public double formatCurrency(double amount) {
        // Apply formatting methods to the amount to ensure it is displayed as
        // currency with two decimal places.
        double twoDecimalPlacesCutoff = this.limitTwoDecimalPlaces(amount);
        return twoDecimalPlacesCutoff;
    }

    //----------------------Limit-Two-Decimal-Places--------------------------
    public double limitTwoDecimalPlaces(double amount) {
        // Convert the amount to a string, format it to two decimal places.
        String amountString = Double.toString(amount);
        String amountStringLimited = amountString.formatted("%.2f");
        // Convert the formatted string back to a double.
        double amountLimited = Double.parseDouble(amountStringLimited);
        // Return the formatted amount.
        return amountLimited;
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
