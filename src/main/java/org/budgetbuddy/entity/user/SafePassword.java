package org.budgetbuddy.entity.user;
//=================================-Imports-==================================
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class SafePassword {
    //============================-Variables-=================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String encodedPassword;
    @Transient // Do not encode this to the database.
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //===========================-Constructors-===============================
    public SafePassword() {
        this.encodedPassword = "Unspecified Password";
    }
    public SafePassword(String unencodedPassword) {
        this.encodedPassword = this.encodePassword(unencodedPassword);
    }
    //=============================-Methods-==================================

    //--------------------------Encode-Password-------------------------------
    public String encodePassword(String unencodedPassword) {
        return this.passwordEncoder.encode(unencodedPassword);
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================
}
