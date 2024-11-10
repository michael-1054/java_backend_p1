package com.alquilercoches.coches.config;


import com.alquilercoches.coches.models.entities.Corporative_users;
import com.alquilercoches.coches.models.services.Corporative_usersService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {



	private final Corporative_usersService users;

	@Autowired
	public SecurityConfig(Corporative_usersService users){
		this.users = users;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}




	@Bean
	public UserDetailsService userDetailsService() {
		PasswordEncoder encoder = passwordEncoder();
		List<Corporative_users> users_list = this.users.findAll();
		List<UserDetails> user_data = new ArrayList<>();
		UserDetails admin = User.builder().passwordEncoder(encoder::encode)
				.username("admin")
				.password("1234")
				.roles("ADMIN", "USER")
				.build();
		user_data.add(admin);
		for(Corporative_users user : users_list){
			UserDetails user_d = User.builder().passwordEncoder(encoder::encode)
					.username(user.getUser())
					.password(user.getPassword())
					.roles("USER")
					.build();
			user_data.add(user_d);
		}

/*		UserDetails user = User.builder().passwordEncoder(encoder::encode)
				.username("user")
				.password("1234")
				.roles("USER")
				.build();
*/


		return new InMemoryUserDetailsManager(user_data);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						// Primero las rutas más específicas
						.requestMatchers("/admin/**").hasRole("ADMIN") // Solo admin accede a /admin/**
						.requestMatchers("/coche", "/alquilar").hasRole("USER") // Acceso solo para USER en estas rutas
						.requestMatchers("/css/**", "/js/**", "/images/**", "/h2-console/**", "/register/**","/fall/**","/register_form/**","/save_user/**").permitAll()  // Recursos públicos
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
