package com.alquilercoches.coches.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		PasswordEncoder encoder = passwordEncoder();

		UserDetails user = User.builder().passwordEncoder(encoder::encode)
				.username("user")
				.password("1234")
				.roles("USER")
				.build();

		UserDetails admin = User.builder().passwordEncoder(encoder::encode)
				.username("admin")
				.password("1234")
				.roles("ADMIN", "USER")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						// Primero las rutas más específicas
						.requestMatchers("/admin/**").hasRole("ADMIN") // Solo admin accede a /admin/**
						.requestMatchers("/coche", "/alquilar").hasRole("USER") // Acceso solo para USER en estas rutas
						.requestMatchers("/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()  // Recursos públicos
						.anyRequest().authenticated()  // Cualquier otra solicitud requiere autenticación
				)
				.formLogin(form -> form
						.loginPage("/login").permitAll()  // Página de login accesible para todos
						.defaultSuccessUrl("/coche", true)  // Redirige a /coche después del login exitoso
				)
				.logout(logout -> logout.permitAll())  // Permitir logout para todos
				.csrf().disable()  // Desactivar CSRF para el entorno de desarrollo (H2)
				.headers().frameOptions().disable();  // Habilitar acceso a la consola H2

		return http.build();
	}
}
