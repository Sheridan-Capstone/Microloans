package ca.sheridancollege.controllers;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Student;
import ca.sheridancollege.repositories.StudentRepository;

@Controller
public class RegistrationController {

	@Autowired
	private StudentRepository userRepo;

	@GetMapping("/")
	public String goToSignUpPage(Model model) {
		model.addAttribute("newUser", new Student());
		return "RegistrationPage.html";
	}

	@PostMapping("/signUp")
	public String handleSignUp(@ModelAttribute Student user, Model model) {
		userRepo.save(user);
		return "redirect:/";
	}

=======
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
>>>>>>> branch 'main' of https://github.com/Sheridan-Capstone/Microloans
}
