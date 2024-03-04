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
        double twoDecimalPlacesCutoff = this.limitTwoDecimalPlaces(amount);
        return twoDecimalPlacesCutoff;
    }

    //----------------------Limit-Two-Decimal-Places--------------------------
    public double limitTwoDecimalPlaces(double amount) {
        String amountString = Double.toString(amount);
        String amountStringLimited = amountString.formatted("%.2f");
        double amountLimited = Double.parseDouble(amountStringLimited);
        return amountLimited;
    }

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
