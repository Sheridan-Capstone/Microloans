package ca.sheridancollege.liuzhun.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paypal.http.annotations.Model;

import ca.sheridancollege.liuzhun.beans.Message;
import ca.sheridancollege.liuzhun.repositories.MessageRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MessageController {
	private MessageRepository messageRepository;
	
	@PostMapping("/secure/message")
	public String SendMessage(Model model, Authentication authentication, @ModelAttribute Message message) {
		message.setApproval(0);
		message.setArchiveStatus(0);
		message.setSender(authentication.getName());
		
		messageRepository.save(message);

		return "redirect:/secure/DonorDashboard/MessageCenter";
	}
}
