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

import static pl.infoshare.clinicweb.user.Role.ADMIN;
import static pl.infoshare.clinicweb.user.Role.DOCTOR;

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
                .roles(ADMIN.name())
                .build();

        UserDetails doctor = User.withUsername("doctor")
                .password(passwordEncoder.encode("doctor"))
                .roles(DOCTOR.name())
                .build();

        return new InMemoryUserDetailsManager(admin, doctor);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/update-patient**",
                                "/update-doctor**",
                                "/delete-doctor**",
                                "/delete-patient**",
                                "/doctor")
                        .hasRole(ADMIN.name())
                        .requestMatchers("/search-doctor**",
                                "/search-patient**",
                                "/search**",
                                "/visit",
                                "/cancel",
                                "/doctors/**",
                                "/patients/**",
                                "/visits/**",
                                "/patient")
                        .hasAnyRole(ADMIN.name(), DOCTOR.name())
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true)
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error=true")
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .csrf((csrf) -> csrf.disable());

        return httpSecurity.build();
    }
}
