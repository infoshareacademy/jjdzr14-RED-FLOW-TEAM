package pl.infoshare.clinicweb.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.infoshare.clinicweb.user.AppUserService;

import static pl.infoshare.clinicweb.user.Role.ADMIN;
import static pl.infoshare.clinicweb.user.Role.DOCTOR;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    String[] staticResources = {
            "/styles/**",
            "/images/**"};

    private final AppUserService userService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers(staticResources).permitAll()
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
                        .usernameParameter("email")
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
