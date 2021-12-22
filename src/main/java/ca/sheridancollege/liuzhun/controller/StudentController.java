package ca.sheridancollege.liuzhun.controller;

import ca.sheridancollege.liuzhun.beans.Message;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.beans.ChangePassword;
import ca.sheridancollege.liuzhun.beans.ChangeProfile;
import ca.sheridancollege.liuzhun.repositories.DonationRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.MessageRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;
import ca.sheridancollege.liuzhun.security.StudentDetailsServiceImpl;
import ca.sheridancollege.liuzhun.services.FilesStorageService;
import lombok.AllArgsConstructor;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@AllArgsConstructor
@Controller
public class StudentController {
	@Autowired
	FilesStorageService storageService;
	
	private DonorRepository donorRepository;
	private DonationRepository donationRepository;
	private StudentRepository studentRepository;
	private MessageRepository messageRepository;
	
	private StudentDetailsServiceImpl studentService;

	@GetMapping("/secure/StudentDashboard")
	public String StudentDashboard(Model model, Authentication authentication) {
		
		String name = authentication.getName();
		System.out.println(studentRepository.findByEmail(name).getEmail());

		String fullName =  (studentRepository.findByEmail(name).getFirstname().toString() + " " + studentRepository.findByEmail(name).getLastname().toString());		
		model.addAttribute("name", name);
		model.addAttribute("username", studentRepository.findByEmail(name).getUsername());
		model.addAttribute("email", studentRepository.findByEmail(name).getEmail());
		model.addAttribute("fieldOfInterest", studentRepository.findByEmail(name).getFieldOfInterest());
		model.addAttribute("postSecondaryName", studentRepository.findByEmail(name).getPostSecondaryName());
		model.addAttribute("highSchoolName", studentRepository.findByEmail(name).getHighSchoolName());
		model.addAttribute("city", studentRepository.findByEmail(name).getCity());
		model.addAttribute("story", studentRepository.findByEmail(name).getStory());
		model.addAttribute("studentName", fullName);
	
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

	
//	@GetMapping("/secure/StudentDashboard/DonationHistory")
//	public String DonationHistory(Model model, Authentication authentication) {
//
//		String name = authentication.getName();
//
//		model.addAttribute("name", name);
//		model.addAttribute("donations", donationRepository.findByDonorEmail(name));
//		
//		return "secure/StudentDashboard/DonationHistory";
//	}
	
	@GetMapping("/secure/StudentDashboard/DonationStatus")
	public String DonationStatus(Model model, Authentication authentication) {

		String name = authentication.getName();

		model.addAttribute("name", name);
		model.addAttribute("donations", donationRepository.findByStudentEmail(name));
		
		return "secure/StudentDashboard/DonationStatus";
	}
	
	@GetMapping("/secure/StudentDashboard/ProfileManager")
	public String ProfileManager(Model model, Authentication authentication) {
		
		String name = authentication.getName();
		
		model.addAttribute("username", studentRepository.findByEmail(name).getUsername());
		model.addAttribute("fname", studentRepository.findByEmail(name).getFirstname());
		model.addAttribute("lname", studentRepository.findByEmail(name).getLastname());
		model.addAttribute("email", studentRepository.findByEmail(name).getEmail());
		model.addAttribute("program", studentRepository.findByEmail(name).getFieldOfInterest());
		model.addAttribute("postSecondary", studentRepository.findByEmail(name).getPostSecondaryName());
		model.addAttribute("highSchoolName", studentRepository.findByEmail(name).getHighSchoolName());
		model.addAttribute("city", studentRepository.findByEmail(name).getCity());
		model.addAttribute("story", studentRepository.findByEmail(name).getStory());

		model.addAttribute("name", name);
		model.addAttribute("changeProfile", new ChangeProfile());
		model.addAttribute("changePassword", new ChangePassword());
		return "secure/StudentDashboard/ProfileManager";
	}
	
	@PostMapping("/secure/StudentDashboard/EditProfile")
	public String UpdateProfile(Model model, Authentication authentication, @ModelAttribute ChangeProfile changeProfile) {
		
		String name = authentication.getName();
		Student student = studentRepository.findByEmail(name);
		
		if(!changeProfile.getUsername().isBlank()) {
			String userName = changeProfile.getUsername();
			student.setUsername(userName);
		}
		if(!changeProfile.getFname().isBlank()) {
			String fname = changeProfile.getFname();
			student.setFirstname(fname);
		}
		if(!changeProfile.getLname().isBlank()) {
			String lname = changeProfile.getLname();
			student.setLastname(lname);
		}
		if(!changeProfile.getHighSchoolName().isBlank()) {
			String highSchool = changeProfile.getHighSchoolName();
			student.setHighSchoolName(highSchool);
		}
		if(!changeProfile.getProgram().isBlank()) {
			String program = changeProfile.getProgram();
			student.setFieldOfInterest(program);
		}
		if(!changeProfile.getPostSecondary().isBlank()) {
			String postSecondary = changeProfile.getPostSecondary();
			student.setPostSecondaryName(postSecondary);
		}
		if(!changeProfile.getStory().isBlank()) {
			String story = changeProfile.getStory();
			student.setStory(story);
		}
		if(!changeProfile.getCity().isBlank()) {
			String city = changeProfile.getCity();
			student.setCity(city);
		}
		
		studentRepository.save(student);
		return "redirect:/secure/StudentDashboard/ProfileManager";
	}

	@GetMapping("/secure/StudentDashboard/DeactivateAccount")
	public String DeactivateAccount(Model model, Authentication authentication) {
		
		String name = authentication.getName();
		
		Student student = studentRepository.findByEmail(name);
		
		student.setEnabled(false);
		
		studentRepository.save(student);
		return "redirect:/logout";
	}

	@PostMapping("/secure/StudentDashboard/ChangePassword")
	public String ChangePassword(Model model, Authentication authentication, @ModelAttribute ChangePassword changePassword) {

		String name = authentication.getName();
		model.addAttribute("changeProfile", new ChangeProfile());
		
		String currentPassword = changePassword.getCurrentPassword();
		String newPassword = changePassword.getNewPassword();
		String newPasswordConfirm = changePassword.getNewPasswordConfirm();
		
		Student student = studentRepository.findByEmail(name);
		
		if(!changePassword.getEmail().isBlank()) {
			String email = changePassword.getEmail();
			student.setEmail(email);
		}
		
		if(!changePassword.getCurrentPassword().isBlank() && !changePassword.getNewPassword().isBlank() && !changePassword.getNewPasswordConfirm().isBlank()) {
			if(!matchPassword(currentPassword, student.getEncryptedPassword())) {
				
				model.addAttribute("message", "Current password is incorrect!");
				
				return "secure/StudentDashboard/ProfileManager";
			}if(!newPassword.equals(newPasswordConfirm)){
				
				model.addAttribute("message", "Password confirmation is incorrect!");

				return "secure/StudentDashboard/ProfileManager";
			}
			
			student.setEncryptedPassword(encodePassword(newPassword));
		}
		
		studentRepository.save(student);
			
		model.addAttribute("message", "Password changed successfully!");

		return "redirect:/logout";
	}
	
	@PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                    Authentication authentication, Model model, RedirectAttributes redirectAttributes) {
		String name = authentication.getName();
		model.addAttribute("name", name);
		model.addAttribute("changeProfile", new ChangeProfile());
		model.addAttribute("changePassword", new ChangePassword());
	
        if (file.isEmpty()) {
        	redirectAttributes.addFlashAttribute("uploadResponse", "Please upload a .pdf file ");
            return "redirect:/secure/StudentDashboard/ProfileManager";
        }
        //pdf lowercase and uppercase
        else if(file.getOriginalFilename().contains(".pdf") || file.getOriginalFilename().contains(".PDF")) {
			String fileDirectory = ("src/main/resources/static/uploads/" + name + "/");
			File directory = new File(fileDirectory);
			storageService.save(directory,fileDirectory,file);
			redirectAttributes.addFlashAttribute("uploadResponse", "You successfully uploaded '" + file.getOriginalFilename() + "'");
        }
        else {
        	redirectAttributes.addFlashAttribute("uploadResponse", "Could not upload file, please select a .pdf ");
        	 return "redirect:/secure/StudentDashboard/ProfileManager";
        }
        return "redirect:/secure/StudentDashboard/ProfileManager";
    }
	
	  @ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.notFound().build();
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
