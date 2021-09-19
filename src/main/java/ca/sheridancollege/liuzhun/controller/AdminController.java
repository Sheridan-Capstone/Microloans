package ca.sheridancollege.liuzhun.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.itextpdf.html2pdf.HtmlConverter;

import ca.sheridancollege.liuzhun.beans.Donation;
import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.beans.Message;
import ca.sheridancollege.liuzhun.repositories.DonationRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.MessageRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class AdminController {
	private MessageRepository messageRepository;
	private DonorRepository donorRepository;
	private DonationRepository donationRepository;
	
	
	@GetMapping("/secure/AdminHomepage")
	public String AdminHomepage(Model model) {

		return "secure/AdminHomepage";
	}

	@GetMapping("/secure/AdminDashboard/MessageCenter/{filter}")
	public String AdminHomepage(Model model, @PathVariable int filter, Authentication authentication) {

		String name = authentication.getName();
		
		List<Message> messages = messageRepository.findAll();
		
		switch(filter) {
			case 0: messages = messages.stream().filter(x -> x.getArchiveStatus() == 0).collect(Collectors.toList()); break;
			case 1: messages = messages.stream().filter(x -> x.getApproval() == 0 && x.getArchiveStatus() == 0).collect(Collectors.toList()); break;
			case 2: messages = messages.stream().filter(x -> x.getApproval() == 1 && x.getArchiveStatus() == 0).collect(Collectors.toList()); break;
			case 3: messages = messages.stream().filter(x -> x.getApproval() == 2 && x.getArchiveStatus() == 0).collect(Collectors.toList()); break;
			case 4: messages = messages.stream().filter(x -> x.getArchiveStatus() == 1).collect(Collectors.toList()); break;
		}
		
		model.addAttribute("name", name);
		model.addAttribute("messages", messages);

		return "secure/AdminDashboard/MessageCenter";
	}
	
	@GetMapping("/secure/AdminDashboard/DonationRecords")
	public String DonationRecords(Model model, Authentication authentication) {
		String name = authentication.getName();
		
		List<Donor> donors = donorRepository.findAll();
		List<Donation> donations = donationRepository.findByDonorEmail(donors.get(0).getEmail());
		
		model.addAttribute("name", name);
		model.addAttribute("donors", donors);
		model.addAttribute("donor", new Donor());

		model.addAttribute("selectedDonor", donors.get(0)); //Empty placeholder
		model.addAttribute("selectedDonorDonation", donations);
		
		return "secure/AdminDashboard/DonationRecords";
	}

	@PostMapping("/secure/AdminDashboard/DonationRecords/")
	public String DonationRecordByEmail(Model model, @ModelAttribute Donor donor, Authentication authentication) {
		String name = authentication.getName();
		
		List<Donor> donors = donorRepository.findAll();
		List<Donation> donations = donationRepository.findByDonorEmail(donor.getEmail());
		
		model.addAttribute("name", name);
		model.addAttribute("donors", donors);
		model.addAttribute("donor", new Donor());

		model.addAttribute("selectedDonor", donor);
		model.addAttribute("selectedDonorDonation", donations);
		
		
		return "secure/AdminDashboard/DonationRecords";
	}
	
	@GetMapping("/secure/AdminDashboard/Recipt")
	public ResponseEntity GetRecipt(Authentication authentication) {

		String name = authentication.getName();

		String fileName = "templates/recipt/recipt.html";
        ClassLoader classLoader = getClass().getClassLoader();
 
        
        File output = new File("recipt" + name + LocalDate.now() + ".pdf");
        
        byte[] bytes;
        
        try {
			File file = new File(classLoader.getResource(fileName).getFile());
			//File filledFile = ReplaceTextInFile(file, name, "3.0");
			
        	
			HtmlConverter.convertToPdf(file, output);
			bytes = Files.readAllBytes(output.toPath());
			
			
			output.delete();
//			filledFile.delete();
			
        }catch(Exception e) {
        	
        	return ResponseEntity.status(500).body("error");
        }
        
		
        return ResponseEntity.ok()
			.contentType(MediaType.APPLICATION_PDF)
			.header("attachment; filename=\"Recipt.pdf\"")
			.body(bytes);
	}
	
	private File ReplaceTextInFile(File file, String name, String amount) throws Exception {
		List<String> lines = new ArrayList<String>();
		String line;

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		while((line = br.readLine()) != null) {
			if(line.contains("{{date}}")) {
				line = line.replace("{{date}}", LocalDate.now().toString());
			}

			if(line.contains("{{donor}}")) {
				line = line.replace("{{donor}}", name);
			}

			if(line.contains("{{amount}}")) {
				line = line.replace("{{amount}}", amount);
			}
			lines.add(line);
		}
		
		fr.close();
		br.close();
		
        File output = new File("filled" + name + LocalDate.now() + ".pdf");
        output.createNewFile();
        
        FileWriter fw = new FileWriter(output.getName());
		BufferedWriter out = new BufferedWriter(fw);

		for(String s : lines) {
			out.write(s);
		}
		out.flush();
		out.close();
		
		return output;
	}
}
