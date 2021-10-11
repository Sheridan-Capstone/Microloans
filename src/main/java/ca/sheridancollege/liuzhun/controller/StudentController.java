package ca.sheridancollege.liuzhun.controller;

import ca.sheridancollege.liuzhun.beans.Message;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.beans.ChangePassword;
import ca.sheridancollege.liuzhun.repositories.DonationRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.MessageRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@AllArgsConstructor
@Controller
public class StudentController {
	
	private DonorRepository donorRepository;
	private DonationRepository donationRepository;
	private StudentRepository studentRepository;
	private MessageRepository messageRepository;

	@GetMapping("/secure/StudentDashboard")
	public String StudentDashboard(Model model, Authentication authentication) {
		
		String name = authentication.getName();
		
		model.addAttribute("name", name);
		
		return "secure/StudentDashboard/index";
	}

	@GetMapping("/secure/StudentDashboard/MessageCenter")
	public String StudentDashboardMessageCenter(Model model, Authentication authentication) {

		String name = authentication.getName();
		
		model.addAttribute("name", name);
		model.addAttribute("donors", donorRepository.findAll());
		model.addAttribute("message", new Message());
		model.addAttribute("messages", messageRepository.findByReceiverAndApproval(name, 1));
		
		return "secure/StudentDashboard/MessageCenter";
	}
	
	@GetMapping("/secure/StudentDashboard/DonationHistory")
	public String DonationHistory(Model model, Authentication authentication) {

		String name = authentication.getName();

		model.addAttribute("name", name);
		model.addAttribute("donations", donationRepository.findByDonorEmail(name));
		
		return "secure/StudentDashboard/DonationHistory";
	}
	
	@GetMapping("/secure/StudentDashboard/ProfileManager")
	public String ProfileManager(Model model, Authentication authentication) {
		
		String name = authentication.getName();

		model.addAttribute("name", name);
		model.addAttribute("changePassword", new ChangePassword());

		return "secure/StudentDashboard/ProfileManager";
	}

	@GetMapping("/secure/StudentDashboard/DeactivateAccount")
	public String DeactivateAccount(Model model, Authentication authentication) {
		
		String name = authentication.getName();
		
		Student student = studentRepository.findByEmail(name);
		
		student.setEnabled(false);
		
		studentRepository.save(student);
		System.out.println(student.getEnabled());
		return "redirect:/logout";
	}

	@PostMapping("/secure/StudentDashboard/ChangePassword")
	public String ChangePassword(Model model, Authentication authentication, @ModelAttribute ChangePassword changePassword) {

		String name = authentication.getName();
		
		String currentPassword = changePassword.getCurrentPassword();
		String newPassword = changePassword.getNewPassword();
		String newPasswordConfirm = changePassword.getNewPasswordConfirm();
		
		Student student = studentRepository.findByEmail(name);
		
		if(!matchPassword(currentPassword, student.getEncryptedPassword())) {
			
			model.addAttribute("message", "Current password is incorrect!");
			
			return "secure/StudentDashboard/ProfileManager";
		}if(!newPassword.equals(newPasswordConfirm)){
			
			model.addAttribute("message", "Password confirmation is incorrect!");

			return "secure/StudentDashboard/ProfileManager";
		}
		
		student.setEncryptedPassword(encodePassword(newPassword));
		studentRepository.save(student);
			
		model.addAttribute("message", "Password changed successfully!");

		return "secure/StudentDashboard/ProfileManager";
	}

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	private Boolean matchPassword(String left, String right) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(left, right);
	}
}
