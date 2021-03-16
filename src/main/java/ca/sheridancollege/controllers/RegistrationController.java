package ca.sheridancollege.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import ca.sheridancollege.beans.User;

@Controller
public class RegistrationController {

	@GetMapping("/")
	public String goToSignUpPage(Model model) {
		User user= new User();
		model.addAttribute("user", user);
		return "RegistrationPage.html";
	}

	@PostMapping("/signUp")
	public String handleSignUp(Model model) {
		return "redirect:/";
	}
	
	@PostMapping("/")
	public ModelAndView registerUserAccount(
	  @ModelAttribute("user") @Valid User user, 
	  HttpServletRequest request, Errors errors) { 
	    
	   

	    return new ModelAndView("Registered Successfully!", "user", user);
	}
}
