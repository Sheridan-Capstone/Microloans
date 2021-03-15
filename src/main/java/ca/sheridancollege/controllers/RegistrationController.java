package ca.sheridancollege.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.beans.User;

@Controller
public class RegistrationController {

	@GetMapping("/")
	public String goToSignUpPage(Model model) {
		model.addAttribute("newUser", new User());
		return "RegistrationPage.html";
	}

	@PostMapping("/signUp")
	public String handleSignUp(Model model) {
		return "redirect:/";
	}
}
