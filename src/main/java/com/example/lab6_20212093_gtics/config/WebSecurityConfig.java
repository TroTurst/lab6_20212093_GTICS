package com.example.lab6_20212093_gtics.config;


import com.example.lab6_20212093_gtics.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // Vistas que se pueden acceder sin estar logueado
                        .requestMatchers("/heroes", "/login", "/home").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Recursos estáticos

                        // Vistas solo para el admin
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/canciones/asignar", "/numeros/asignar").hasAuthority("ADMIN")

                        // Vistas para usuario y admin
                        .requestMatchers("/intenciones/registrar", "/reservas/**").hasAnyAuthority("USUARIO", "ADMIN")
                        .requestMatchers("/juegos/**").hasAnyAuthority("USUARIO", "ADMIN")

                        // En caso no estes puedas ver una vista
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // Redirección tras login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}