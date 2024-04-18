package org.budgetbuddy.entity.user;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.budgetbuddy.exception.UnauthorizedPasswordAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class SafePassword {
    //============================-Variables-=================================
    @Id
    String encodedPassword;
    @Transient // Do not encode this to the database.
    BCryptPasswordEncoder passwordEncoder;
    //===========================-Constructors-===============================
    public SafePassword() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.encodedPassword = "Unspecified Password";
    }
    public SafePassword(String unencodedPassword) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.encodedPassword = this.encodePassword(unencodedPassword);
    }
    //=============================-Methods-==================================

    //--------------------------Encode-Password-------------------------------
    public String encodePassword(String unencodedPassword) {
        return this.passwordEncoder.encode(unencodedPassword);
    }
    //--------------------Compare-Unencoded-To-Encoded------------------------
    public boolean compareUnencodedPassword(String unencodedPassword) {
        return this.passwordEncoder.matches(unencodedPassword, this.encodedPassword);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof SafePassword comparedSafePassword) {
            return this.encodedPassword.equals(comparedSafePassword.encodedPassword);
        }
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        return this.encodedPassword.hashCode();
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        throw new UnauthorizedPasswordAccessException();
    }
    //=============================-Getters-==================================
    public String getEncodedPassword() {
        return this.encodedPassword;
    }
    //=============================-Setters-==================================
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}
