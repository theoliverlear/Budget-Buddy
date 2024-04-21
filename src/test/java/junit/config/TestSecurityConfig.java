package junit.config;
//=================================-Imports-==================================

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@TestConfiguration
@EnableWebSecurity
@Profile("test")
public class TestSecurityConfig {
    //==============================-Beans-===================================

    //----------------------------Filter-Chain--------------------------------
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
        return http.build();
    }
}
