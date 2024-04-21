package org.budgetbuddy.entity.tax;
//=================================-Imports-==================================
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
        if (this == obj) return true;
        if (obj instanceof Tax tax) {
            boolean idIsSame = this.id.equals(tax.id);
            boolean typeIsSame = this.type.equals(tax.type);
            boolean rateIsSame = this.rateAsDecimal == tax.rateAsDecimal;
            return idIsSame && typeIsSame && rateIsSame;
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        return "%s: %.2f%%".formatted(this.type, this.rateAsDecimal * 100);
    }
    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
