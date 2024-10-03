package pl.infoshare.clinicweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails doctor = User.withUsername("doctor")
                .password(passwordEncoder.encode("doctor"))
                .roles("DOCTOR")
                .build();

        UserDetails patient = User.withUsername("patient")
                .password(passwordEncoder.encode("patient"))
                .roles("PATIENT")
                .build();
        return new InMemoryUserDetailsManager(admin, doctor, patient);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/visit").permitAll()
                .requestMatchers("/doctors/**").hasAnyRole("ADMIN","DOCTOR")
                .requestMatchers("/patients/**").hasAnyRole("ADMIN","DOCTOR")
                .requestMatchers("/visits/**").hasAnyRole("ADMIN","DOCTOR")
                .requestMatchers("/doctor").hasAnyRole("ADMIN","DOCTOR")
                .requestMatchers("/patient").hasAnyRole("ADMIN","DOCTOR")
                .and()
                .formLogin(f -> f.failureUrl("/login").defaultSuccessUrl("/", true))
                .logout(l -> l.logoutUrl("/logout").logoutSuccessUrl("/"));

        return httpSecurity.build();
    }
}
