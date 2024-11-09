package com.alquilercoches.coches.controllers;

import com.alquilercoches.coches.appdata.AppData;
import com.alquilercoches.coches.models.entities.Corporative_users;
import com.alquilercoches.coches.models.services.Corporative_usersService;
import com.alquilercoches.coches.util.paginator.PageRender;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@SessionAttributes("corporative_users")
@RequestMapping("/corporative_users")
public class Corporative_usersController {

	private final AppData appData;
	private final Corporative_usersService corporativeUsersService;







	public static final String OPGEN = "CORPORATIVE_USERS";

	public Corporative_usersController(


										 Corporative_usersService corporativeUsersService,
									     AppData applicationData
		   
		   		 
			) {
		this.appData = applicationData;
		this.corporativeUsersService = corporativeUsersService;
		
		
		

		
	}
	public List<Corporative_users> getAllUsers(){
		return this.corporativeUsersService.findAll();
	}
}

		
	/*
	@GetMapping({ "", "/", "/list", "/list/{page}" })
	public String list(@PathVariable(name = "page", required = false) Integer page, Model model) {
	
		if (page == null)
			page = 0;
		
		fillApplicationData(model,"LIST");
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Corporative_users> pageCorporative_users = corporativeUsersService.findAll(pageRequest);
		PageRender<Corporative_users> paginator = new PageRender<>("/corporative_users/list",pageCorporative_users,5);
		

		model.addAttribute("num_corporative_users", corporativeUsersService.count());
		model.addAttribute("list_corporative_users", pageCorporative_users);
		model.addAttribute("paginator",paginator);
		
		model.addAttribute("actualpage", page);
		
		return "corporative_users/list";
	}
	
	@GetMapping({ "/formcr", "/formcr/{page}" })
	public String form(@PathVariable(name = "page", required = false) Integer page, Model model) {
		Corporative_users corporative_users = new Corporative_users();
		model.addAttribute("corporative_users",corporative_users);
		
		if (page == null)
			page = 0;
		model.addAttribute("actualpage", page);
		
		fillApplicationData(model,"CREATE");
		
		return "corporative_users/form";
	}
	
	@GetMapping({ "/formup/{id}", "/formup/{id}/{page}" })
	public String form(@PathVariable(name = "id") Long id, @PathVariable(name = "page", required = false) Integer page, Model model, RedirectAttributes flash) {
		if (page == null)
			page = 0;
		Corporative_users corporative_users = corporativeUsersService.findOne(id);
		if(corporative_users==null) {
			flash.addFlashAttribute("error","Data not found");
			return "redirect:/corporative_users/list/" + page;
		}
		
		model.addAttribute("corporative_users", corporative_users);
		
		model.addAttribute("actualpage", page);
		
		fillApplicationData(model,"UPDATE");
		
		return "corporative_users/form";
	}
	
	
	@PostMapping("/form/{page}")
	@Secured("ROLE_ADMIN")
	public String form(@Valid Corporative_users corporative_users,
			           BindingResult result, 
					   
					   Model model,
					   
					   @PathVariable(name = "page") int page,
					   RedirectAttributes flash,
					   SessionStatus status) {
		
		boolean creating;
		
		if(corporative_users.getId()==null) {
			fillApplicationData(model,"CREATE");
			creating = true;
		} else {
			fillApplicationData(model,"UPDATE");
			creating = false;
		}
		
		String msg = (corporative_users.getId()==null) ? "Creation successful" : "Update successful";
		
		if(result.hasErrors()) {
			model.addAttribute("actualpage", page);
			return "corporative_users/form";
		}
		
		
		
		
		
		corporativeUsersService.save(corporative_users);
		status.setComplete();
		flash.addFlashAttribute("success",msg);
		
		if (creating)
			page = lastPage();
		
		return "redirect:/corporative_users/list/" + page;
	}
	
	
	
	

	@Secured("ROLE_ADMIN")
	@GetMapping({ "/delete/{id}", "/delete/{id}/{page}" })
	public String delete(@PathVariable(name = "id") Long id,
			@PathVariable(name = "page", required = false) Integer page, RedirectAttributes flash) {
		
		if (page == null)
			page = 0;
		
		if(id>0) { 			
			Corporative_users corporative_users = corporativeUsersService.findOne(id);
			
			if(corporative_users != null) {
*/
		/* Only if there is required relation with this entity */			
				
		
		/* Only if there is no required relation with this entity, or there is a
		 * required relation but no entity related at the moment, (checked before) */
				
		
		/* Relations revised, the entity can be removed */
	/*
				corporativeUsersService.remove(id);
			} else {
				flash.addFlashAttribute("error","Data not found");
				return "redirect:/corporative_users/list/" + page;
			}
			
			
						
			flash.addFlashAttribute("success","Deletion successful");
		}
		
		return "redirect:/corporative_users/list/" + page;
	}
	
	@GetMapping({ "/view/{id}", "/view/{id}/{page}" })
	public String view(@PathVariable(name = "id") Long id,
			@PathVariable(name = "page", required = false) Integer page, Model model, RedirectAttributes flash) {

		if (page == null)
			page = 0;
		
		if (id > 0) {
			Corporative_users corporative_users = corporativeUsersService.findOne(id);

			if (corporative_users == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/corporative_users/list/" + page;
			}

			model.addAttribute("corporative_users", corporative_users);
			model.addAttribute("actualpage", page);
			fillApplicationData(model, "VIEW");
			return "corporative_users/view";
			
		}

		return "redirect:/corporative_users/list/" + page;
	}
	
	
	@GetMapping("/viewimg/{id}/{imageField}")
	public String viewimg(@PathVariable Long id, @PathVariable String imageField, Model model, RedirectAttributes flash) {

		if (id > 0) {
			Corporative_users corporative_users = corporativeUsersService.findOne(id);

			if (corporative_users == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/corporative_users/list";
			}

			model.addAttribute("corporative_users", corporative_users);
			fillApplicationData(model, "VIEWIMG");
			model.addAttribute("backOption",true);
			model.addAttribute("imageField",imageField);
			
			return "corporative_users/viewimg";
			
		}

		return "redirect:/corporative_users/list";
	}
	
	
	
	
	private int lastPage() {
		Long nReg = corporativeUsersService.count();
		int nPag = (int) (nReg / 10);
		if (nReg % 10 == 0)
			nPag--;
		return nPag;
	}
	
	private void fillApplicationData(Model model, String screen) {
		model.addAttribute("applicationData",appData);
		model.addAttribute("optionCode",OPGEN);
		model.addAttribute("screen",screen);
	}
	
		
}
*/