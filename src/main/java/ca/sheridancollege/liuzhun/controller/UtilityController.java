package ca.sheridancollege.liuzhun.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@RestController
@RequestMapping("/secure/utility")
public class UtilityController {
	
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

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/approveMessage")
	public void approveMessage(@RequestBody String id, Authentication authentication) {
		Message message = messageRepository.findById(Long.parseLong(id)).get();
		
		message.setApproval(1);
		messageRepository.save(message);
	}

	@PostMapping(consumes = {MediaType.TEXT_PLAIN_VALUE}, value = "/rejectMessage")
	public void rejectMessage(@RequestBody String id, Authentication authentication) {
		Message message = messageRepository.findById(Long.parseLong(id)).get();
		
		message.setApproval(2);
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
