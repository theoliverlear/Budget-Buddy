package org.budgetbuddy.entity.tax;
//=================================-Imports-==================================
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.budgetbuddy.convert.entity.tax.TaxKeyDeserializer;

@Entity
@Getter
@Setter
@JsonDeserialize(keyUsing = TaxKeyDeserializer.class)
public class Tax {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String type;
    double rateAsDecimal;
    //===========================-Constructors-===============================
    public Tax() {
        this.rateAsDecimal = 0.0;
    }
    public Tax(double rateAsDecimal) {
        this.type = "";
        this.rateAsDecimal = rateAsDecimal;
    }
    public Tax(String type, double rateAsDecimal) {
        this.type = type;
        this.rateAsDecimal = rateAsDecimal;
    }
    //=============================-Methods-==================================

    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of Tax. If it is, cast it to a
        // Tax object.
        if (obj instanceof Tax tax) {
            // Check if the fields of the Tax objects are equal.
            boolean idIsSame = this.id.equals(tax.id);
            boolean typeIsSame = this.type.equals(tax.type);
            boolean rateIsSame = this.rateAsDecimal == tax.rateAsDecimal;
            return idIsSame && typeIsSame && rateIsSame;
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the id field.
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        // Return a formatted string with the type and rate of the Tax.
        return "%s: %.2f%%".formatted(this.type, this.rateAsDecimal * 100);
    }
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
