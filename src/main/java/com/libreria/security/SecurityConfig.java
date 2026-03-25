package com.libreria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Desactivamos por ahora para facilitar las pruebas
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin estado (para JWT luego)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Liberamos Swagger
                .anyRequest().authenticated() // Todo lo demás requiere login
            )
            .httpBasic(Customizer.withDefaults()) // Permite login básico (User/Pass) en el Header
            .build();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
	    UserDetails user = User.builder()
	        .username("admin")
	        .password(passwordEncoder().encode("admin123"))
	        .roles("ADMIN")
	        .build();

	    return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(); // El estándar para encriptar claves
	}

}
