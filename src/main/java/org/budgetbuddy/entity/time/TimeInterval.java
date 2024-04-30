package org.budgetbuddy.entity.time;
//=================================-Imports-==================================
import jakarta.persistence.*;

@Entity
public class TimeInterval {
    //============================-Variables-=================================
    @Id
    double triggersPerYear;
    String intervalType;
    //============================-Constants-=================================
    @Transient
    public static final TimeInterval DAILY = new TimeInterval(365);
    @Transient
    public static final TimeInterval WEEKLY = new TimeInterval(52);
    @Transient
    public static final TimeInterval BIWEEKLY = new TimeInterval(26);
    @Transient
    public static final TimeInterval MONTHLY = new TimeInterval(12);
    @Transient
    public static final TimeInterval BIMONTHLY = new TimeInterval(6);
    @Transient
    public static final TimeInterval QUARTERLY = new TimeInterval(4);
    @Transient
    public static final TimeInterval SEMIANNUALLY = new TimeInterval(2);
    @Transient
    public static final TimeInterval ANNUALLY = new TimeInterval(1);
    @Transient
    public static final TimeInterval BIENNIALLY = new TimeInterval(0.5);
    //===========================-Constructors-===============================
    public TimeInterval() {
        this.triggersPerYear = 0;
        this.intervalType = "Custom";
    }
    public TimeInterval(double triggersPerYear) {
        this.triggersPerYear = triggersPerYear;
        this.intervalType = this.determineIntervalType();
    }
    public TimeInterval(double triggersPerYear, String intervalType) {
        this.triggersPerYear = triggersPerYear;
        this.intervalType = intervalType;
    }
    //=============================-Methods-==================================

    //----------------------Determine-Interval-Type---------------------------
    public String determineIntervalType() {
        // NOTE: Switch statements do not work with double values. A long
        //       if/else chain is easiest alternative.
        // Look for common triggers per year values to determine the
        // appropriate name for the interval type.
        if (this.triggersPerYear == 365) {
            return "Daily";
        } else if (this.triggersPerYear == 52) {
            return "Weekly";
        } else if (this.triggersPerYear == 26) {
            return "Biweekly";
        } else if (this.triggersPerYear == 12) {
            return "Monthly";
        } else if (this.triggersPerYear == 6) {
            return "Bimonthly";
        } else if (this.triggersPerYear == 4) {
            return "Quarterly";
        } else if (this.triggersPerYear == 2) {
            return "Semiannually";
        } else if (this.triggersPerYear == 1) {
            return "Annually";
        } else if (this.triggersPerYear == 0.5) {
            return "Biennially";
        } else {
            return "Custom";
        }
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of TimeInterval. If it is, cast
        // it to a TimeInterval object.
        if (obj instanceof TimeInterval timeInterval) {
            // Check if the fields of the TimeInterval objects are equal.
            return this.triggersPerYear == timeInterval.triggersPerYear;
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the triggers per year.
        return Double.hashCode(this.triggersPerYear);
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        // Return a formatted string with the triggers per year.
        return "Occurs %f times per year".formatted(this.triggersPerYear);
    }
    //=============================-Getters-==================================
    public double getTriggersPerYear() {
        return this.triggersPerYear;
    }
    public String getIntervalType() {
        return this.intervalType;
    }
    //=============================-Setters-==================================
    public void setTriggersPerYear(double triggersPerYear) {
        this.triggersPerYear = triggersPerYear;
    }
    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }
}