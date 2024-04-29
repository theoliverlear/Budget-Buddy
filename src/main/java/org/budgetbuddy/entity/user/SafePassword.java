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
        // Encode the password with the BCryptPasswordEncoder and return it.
        return this.passwordEncoder.encode(unencodedPassword);
    }
    //--------------------Compare-Unencoded-To-Encoded------------------------
    public boolean compareUnencodedPassword(String unencodedPassword) {
        // Compare the unencoded password to the encoded password.
        return this.passwordEncoder.matches(unencodedPassword, this.encodedPassword);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------
    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same. If they are, return
        // true.
        if (this == obj) return true;
        // Check if the object is an instance of SafePassword. If it is, cast
        // it to a SafePassword object.
        if (obj instanceof SafePassword comparedSafePassword) {
            // Check if the customizable fields of the SafePassword objects
            // are equal.
            return this.encodedPassword.equals(comparedSafePassword.encodedPassword);
        }
        // If we have reached this point, the objects are not instances of the
        // same class and are not equal, so we return false.
        return false;
    }
    //------------------------------Hash-Code---------------------------------
    @Override
    public int hashCode() {
        // Return the hashcode of the encoded password.
        return this.encodedPassword.hashCode();
    }
    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        // For security reasons, we do not want to return the encoded
        // password. We cease the propgram at any attempt to access the
        // encoded password.
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
