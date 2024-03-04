package org.budgetbuddy.config;
//=================================-Imports-==================================
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class BudgetBuddyConfig {
    //============================-Variables-=================================

    //===========================-Constructors-===============================

    //=============================-Methods-==================================

    //==============================-Beans-===================================

    //--------------------------Get-Data-Source-------------------------------
    @Bean
    public DataSource getDataSource() {
        final String DB_USERNAME = System.getenv("DB_USERNAME");
        final String DB_PASSWORD = System.getenv("DB_PASSWORD");
        final String DB_URL = System.getenv("DB_URL");
        return DataSourceBuilder.create()
                                .driverClassName("org.h2.Driver")
                                .url(DB_URL)
                                .username(DB_USERNAME)
                                .password(DB_PASSWORD)
                                .build();
    }
    //============================-Overrides-=================================

    //------------------------------Equals------------------------------------

    //------------------------------Hash-Code---------------------------------

    //------------------------------To-String---------------------------------

    //=============================-Getters-==================================

    //=============================-Setters-==================================

}
