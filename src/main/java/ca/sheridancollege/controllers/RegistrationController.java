package ca.sheridancollege.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.beans.Student;
import ca.sheridancollege.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegistrationController {

	private StudentRepository userRepo;

	@GetMapping("/")
	public String goToSignUpPage(Model model) {
		model.addAttribute("newUser", new Student());
		return "RegistrationPage";
	}

	@PostMapping("/signUp")
	public String handleSignUp(@ModelAttribute Student user, Model model) {
		userRepo.save(user);
		return "redirect:/";
	}

}
