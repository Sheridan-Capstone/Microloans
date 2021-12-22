package ca.sheridancollege.liuzhun.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.paypal.http.annotations.Model;

import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.beans.Message;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.MessageRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MessageController {
	private MessageRepository messageRepository;
	@Autowired
	private DonorRepository donorRepo;
	@Autowired
	private StudentRepository studentRepo;
	
	
	@PostMapping("/secure/message")
	public String SendMessage(Model model, Authentication authentication, @ModelAttribute Message message) {
		message.setApproval(0);
		message.setArchiveStatus(0);
		message.setSender(authentication.getName());		
		
		
		List<String> roleList = new ArrayList<String>();
		
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		
		if(roleList.get(0).toString().equals("ROLE_DONOR")) {
			message.setSenderEmail(donorRepo.findByUsername(authentication.getName()).getEmail()); 
		}
		else {
			message.setSenderEmail(studentRepo.findByUsername(authentication.getName()).getEmail()); 
		}		
		
		messageRepository.save(message);
	
		if(roleList.get(0).toString().equals("ROLE_DONOR")) {
			
			return "redirect:/secure/DonorDashboard/MessageCenter";
		}
		else {
			studentRepo.findByUsername(authentication.getName()).getEmail(); 
			return "redirect:/secure/StudentDashboard/MessageCenter";
		}
		
		
	}
}
