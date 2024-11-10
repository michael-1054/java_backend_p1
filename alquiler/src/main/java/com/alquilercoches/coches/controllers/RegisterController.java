package com.alquilercoches.coches.controllers;

import com.alquilercoches.coches.appdata.AppDataImpl;
import com.alquilercoches.coches.models.entities.Corporative_users;
import com.alquilercoches.coches.models.services.Corporative_usersService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import com.alquilercoches.coches.appdata.AppData;
import org.springframework.security.core.userdetails.UserDetailsService;



import java.security.Principal;

@Controller
public class RegisterController {

	private final AppData appData;
	private final Corporative_usersService corporativeUsersService;

	public RegisterController(AppDataImpl appData,Corporative_usersService corporativeUsersService ) {
		this.appData = appData;
		this.corporativeUsersService = corporativeUsersService;
	}


	@GetMapping("/register")
	public String register(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {

		if (principal != null) {
			SecurityContextHolder.clearContext();

			return "redirect:/";
		}
		if (logout != null)
			model.addAttribute("success", "You have logged out");

		if (error != null)
			model.addAttribute("error", "Wrong username or password");

		model.addAttribute("applicationData", appData);

		return "login/register";
	}

	@PostMapping("/register_form")
	public String register_form(@RequestParam("username") String user,
								@RequestParam("password") String password,
								Model model, Principal principal, RedirectAttributes flash){
		if (userExists(user)) {
			model.addAttribute("error", "Username already exists!");
			//return "login/register"; // Volver al formulario si el usuario ya existe
		}

		// Agregar el nuevo usuario al mapa de usuarios en SecurityConfig
		this.corporativeUsersService.save(user,password);

		// Limpiar el contexto de seguridad para permitir que el nuevo usuario inicie sesión inmediatamente

		// Redirigir a la página de login
		return "redirect:login/login";
	}



	public boolean userExists(String user) {
		for(Corporative_users c_user :this.corporativeUsersService.findAll()){
			if(c_user.getUser() == user) return true;
		};
		return false;
	}

}

