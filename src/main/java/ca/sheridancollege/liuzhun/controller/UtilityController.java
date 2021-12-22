	package ca.sheridancollege.liuzhun.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.liuzhun.beans.Donation;
import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.beans.Message;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.repositories.DonationRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.MessageRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;

import org.springframework.mail.javamail.JavaMailSender;


@RestController
@RequestMapping("/secure/utility")
public class UtilityController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	DonationRepository donationRepository;

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/addToFavourite")
	public void addToFavourite(@RequestBody String id, Authentication authentication) {
		Donor donor = donorRepository.findByEmail(authentication.getName());
		
		Student student = studentRepository.findById(Long.parseLong(id)).get();
		
		if(!donor.getFavouriteStudents().contains(student)) {
			donor.getFavouriteStudents().add(student);
			donorRepository.save(donor);
		}
		
	}

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/removeFavourite")
	public void removeFavourite(@RequestBody String id, Authentication authentication) {
		Donor donor = donorRepository.findByEmail(authentication.getName());
		
		Student student = studentRepository.findById(Long.parseLong(id)).get();
		
		if(donor.getFavouriteStudents().contains(student)) {
			donor.getFavouriteStudents().remove(student);
			donorRepository.save(donor);
		}

	}
	
	 public void approveEmail(String recipientEmail, String msg)
	            throws MessagingException, UnsupportedEncodingException {
	        MimeMessage message = mailSender.createMimeMessage();              
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	         
	        helper.setFrom("microloans2021test@gmail.com", "Support");
	        helper.setTo(recipientEmail);
	         
	        String subject = "Message Approval";
	         
	        String content = "<p>Hello,</p>"
	                + "<p>Your message has been approved</p>"
	                + "<p>Your message:</p>"
	                + msg
	                + "<br>"
	                + "<p>Message sent to recipent"
	                + "Thank you</p>";
	         
	        helper.setSubject(subject);
	         
	        helper.setText(content, true);
	         
	        mailSender.send(message);
	    }
	 
	 public void rejectEmail(String recipientEmail, String msg)
	            throws MessagingException, UnsupportedEncodingException {
	        MimeMessage message = mailSender.createMimeMessage();              
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	         
	        helper.setFrom("microloans2021test@gmail.com", "Support");
	        helper.setTo(recipientEmail);
	         
	        String subject = "Message Rejected";
	         
	        String content = "<p>Hello,</p>"
	                + "<p>Your message has been rejected</p>"
	                + "<p>Your message:</p>"
	                + msg
	                + "<br>"
	                + "<p>Please contact support if an error has been made"
	                + "Thank you</p>";
	         
	        helper.setSubject(subject);
	         
	        helper.setText(content, true);
	         
	        mailSender.send(message);
	    }

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/approveMessage")
	public void approveMessage(@RequestBody String id, Authentication authentication) {
		Message message = messageRepository.findById(Long.parseLong(id)).get();
		
		message.setApproval(1);
		try {
			approveEmail(message.getSenderEmail(), message.getBody());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		messageRepository.save(message);
	}

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/rejectMessage")
	public void rejectMessage(@RequestBody String id, Authentication authentication) {
		Message message = messageRepository.findById(Long.parseLong(id)).get();
		
		message.setApproval(2);
		try {
			rejectEmail(message.getSenderEmail(), message.getBody());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageRepository.save(message);
	}

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/archiveMessage")
	public void archiveMessage(@RequestBody String id, Authentication authentication) {
		Message message = messageRepository.findById(Long.parseLong(id)).get();
		
		message.setArchiveStatus(1);
		messageRepository.save(message);
	}

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/removeMessage")
	public void removeMessage(@RequestBody String id, Authentication authentication) {
		Message message = messageRepository.findById(Long.parseLong(id)).get();
		
		messageRepository.delete(message);
	}
	
	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/approveApplication")
	public void approveApplication(@RequestBody String id, Authentication authentication) {
		Student student = studentRepository.findById(Long.parseLong(id)).get();
		
		student.setApproval(1);
		studentRepository.save(student);
	}
	
	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/rejectApplication")
	public void rejectApplication(@RequestBody String id, Authentication authentication) {
		Student student = studentRepository.findById(Long.parseLong(id)).get();
		
		student.setApproval(2);
		studentRepository.save(student);
	}

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/saveDonation")
	public void saveDonation(@RequestBody String donation, Authentication authentication) {
		Donation d = new Donation();

		String[] info = donation.split(","); //donor,amount,student
		
		if(info.length != 3) {
			return;
		}
		
		if(!info[0].equals(authentication.getName())) {
			return;
		}
		
		try {
			d.setDonorEmail(info[0]);
			d.setAmount(Double.parseDouble(info[1]));
			d.setStudentEmail(info[2]);
			d.setDate(LocalDateTime.now());
			
			donationRepository.save(d);
		}catch(Exception e) {
			return;
		}
	}

}
