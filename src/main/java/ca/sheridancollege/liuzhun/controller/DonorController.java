package ca.sheridancollege.liuzhun.controller;

import ca.sheridancollege.liuzhun.beans.Message;
import ca.sheridancollege.liuzhun.beans.ChangePassword;
import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.repositories.DonationRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.MessageRepository;
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
public class DonorController {

	private DonorRepository donorRepository;
	private DonationRepository donationRepository;
	private MessageRepository messageRepository;

	@GetMapping("/secure/DonorDashboard")
	public String DonorDashboard(Model model, Authentication authentication) {
		
		String name = authentication.getName();
		
		model.addAttribute("name", name);
		model.addAttribute("favouriteStudents", donorRepository.findByEmail(name).getFavouriteStudents());
		
		return "secure/DonorDashboard/index";
	}

	@GetMapping("/secure/DonorDashboard/MessageCenter")
	public String DonorDashboardMessageCenter(Model model, Authentication authentication) {

		String name = authentication.getName();
		
		model.addAttribute("name", name);
		model.addAttribute("students", donorRepository.findByEmail(name).getFavouriteStudents());
		model.addAttribute("message", new Message());
		model.addAttribute("messages", messageRepository.findByReceiverAndApproval(name, 1));
		
		return "secure/DonorDashboard/MessageCenter";
	}
	
	@GetMapping("/secure/DonorDashboard/FavouriteStudent")
	public String FavouriteStudent(Model model, Authentication authentication) {
		
		String name = authentication.getName();

		model.addAttribute("name", name);
		model.addAttribute("favouriteStudents", donorRepository.findByEmail(name).getFavouriteStudents());

		return "secure/DonorDashboard/FavouriteStudent";
	}
	
	@GetMapping("/secure/DonorDashboard/DonationStatus")
	public String DonationStatus(Model model, Authentication authentication) {

		String name = authentication.getName();

		model.addAttribute("name", name);
		model.addAttribute("donations", donationRepository.findByDonorEmail(name));
		
		return "secure/DonorDashboard/DonationStatus";
	}
	
	@GetMapping("/secure/DonorDashboard/ProfileManager")
	public String ProfileManager(Model model, Authentication authentication) {
		
		String name = authentication.getName();

		model.addAttribute("name", name);
		model.addAttribute("changePassword", new ChangePassword());

		return "secure/DonorDashboard/ProfileManager";
	}

	@GetMapping("/secure/DonorDashboard/DeactivateAccount")
	public String DeactivateAccount(Model model, Authentication authentication) {
		
		String name = authentication.getName();
		
		Donor donor = donorRepository.findByEmail(name);
		
		donor.setIsActive(false);
		
		donorRepository.save(donor);

		return "redirect:/logout";
	}

	@PostMapping("/secure/DonorDashboard/ChangePassword")
	public String ChangePassword(Model model, Authentication authentication, @ModelAttribute ChangePassword changePassword) {

		String name = authentication.getName();
		
		String currentPassword = changePassword.getCurrentPassword();
		String newPassword = changePassword.getNewPassword();
		String newPasswordConfirm = changePassword.getNewPasswordConfirm();
		
		Donor donor = donorRepository.findByEmail(name);
		
		if(!matchPassword(currentPassword, donor.getEncryptedPassword())) {
			
			model.addAttribute("message", "Current password is incorrect!");
			
			return "secure/DonorDashboard/ProfileManager";
		}if(!newPassword.equals(newPasswordConfirm)){
			
			model.addAttribute("message", "Password confirmation is incorrect!");

			return "secure/DonorDashboard/ProfileManager";
		}
		
		donor.setEncryptedPassword(encodePassword(newPassword));
		donorRepository.save(donor);
			
		model.addAttribute("message", "Password changed successfully!");

		return "secure/DonorDashboard/ProfileManager";
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
