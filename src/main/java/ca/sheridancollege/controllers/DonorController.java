package ca.sheridancollege.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.repositories.DonorRepository;
import ca.sheridancollege.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Controller
@AllArgsConstructor
public class DonorController {

	
	private DonorRepository donorRepository;
	private StudentRepository studentRepository;
	
	@GetMapping("/donorPage")
	public String goToDonorPage(Model model) {

		model.addAttribute("students", studentRepository.findAll());
		
		return "DonorPage";
	}
	
	@PostMapping("/donate")
	public String donate(@RequestParam Integer professor, @RequestParam String donationType) {
		return "donationconfirmation";
	}
	
}
