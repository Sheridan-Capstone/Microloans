package ca.sheridancollege.liuzhun.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.liuzhun.beans.Admin;
import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.beans.StudentFilter;
import ca.sheridancollege.liuzhun.repositories.RoleRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;
import ca.sheridancollege.liuzhun.repositories.AdminRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.FieldOfInterestRepository;
import ca.sheridancollege.liuzhun.repositories.HighSchoolRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class HomeController {

	private DonorRepository donorRepository;
	private StudentRepository studentRepository;
	private AdminRepository adminRepository;
	private RoleRepository roleRepository;
	private HighSchoolRepository highSchoolRepository;
	private FieldOfInterestRepository fieldOfInterestRepository;

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("students", studentRepository.findAll().subList(0, 6));
		return "index";
	}

	@GetMapping("/secure")
	public String secureIndex(Model model, Authentication authentication) {

		String name = authentication.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("name", name);
		model.addAttribute("roleList", roleList);

		if (roleList.get(0).toString().equals("ROLE_DONOR")) {

			model.addAttribute("students", studentRepository.findAll());
			model.addAttribute("highSchools", highSchoolRepository.findAll());
			model.addAttribute("fieldOfInterests", fieldOfInterestRepository.findAll());
			model.addAttribute("studentFilter", new StudentFilter());

			return "secure/DonorHomepage";
		} else if (roleList.get(0).toString().equals("ROLE_STUDENT")) {
			String fullName =  (studentRepository.findByEmail(name).getFirstname().toString());
			return "redirect:/secure/StudentDashboard";
		} else if (roleList.get(0).toString().equals("ROLE_ADMIN")) {
			return "redirect:/secure/AdminDashboard/MessageCenter/1";
		} else {
			return "secure/index";
		}
	}

	@GetMapping("/secure/Home/{filter}")
	public String filter(Model model, @PathVariable String filter, Authentication authentication) {

		String name = authentication.getName();
		model.addAttribute("name", name);
		model.addAttribute("students", studentRepository.findBySchoolType(filter));
		model.addAttribute("highSchools", highSchoolRepository.findAll());
		model.addAttribute("fieldOfInterests", fieldOfInterestRepository.findAll());
		model.addAttribute("studentFilter", new StudentFilter());

		return "secure/DonorHomepage";
	}

	@PostMapping("/secure/Home/filter")
	public String advancedFilter(Model model, Authentication authentication,
			@ModelAttribute StudentFilter studentFilter) {

		List<Student> studentList = studentFilter.filter(studentRepository.findAll());
		String name = authentication.getName();
		model.addAttribute("name", name);
		model.addAttribute("students", studentList);
		model.addAttribute("highSchools", highSchoolRepository.findAll());
		model.addAttribute("fieldOfInterests", fieldOfInterestRepository.findAll());
		model.addAttribute("studentFilter", new StudentFilter());

		return "secure/DonorHomepage";
	}

	@GetMapping("/secure/StudentDetails/{id}")
	public String StudentDetails(Model model, @PathVariable Long id, Authentication authentication) {
		String name = authentication.getName();
		model.addAttribute("name", name);
		model.addAttribute("student", studentRepository.findById(id).get());

		return "secure/StudentDetails";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/permissiondenied")
	public String permissiondenied() {
		return "/error/permissiondenied";
	}

	@GetMapping("/users/check")
	public String check() {
		return "/users/check";
	}

	@GetMapping("/register")
	public String goRegistration() {

		return "register";

	}

	@PostMapping("/register")
	public String doRegistration(@RequestParam String username, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String account, @RequestParam String program, @RequestParam String postSecondaryName, @RequestParam String highSchool, @RequestParam String story) {

		if (account.equals("donor")) {;
			Donor donor = new Donor(username, firstname, lastname, email, encodePassword(password), true, true);
			donor.getRoleList().add(roleRepository.findByRolename("ROLE_DONOR"));
			donorRepository.save(donor);
		} else if (account.equals("student")) {
			Student student = new Student(username, firstname, lastname, email, encodePassword(password), 0, "", true, program, postSecondaryName, highSchool, story);
			student.setUsername(username);
			student.setEmail(email);
			student.setEncryptedPassword(encodePassword(password));
			student.setEnabled(true);

			student.getRoleList().add(roleRepository.findByRolename("ROLE_STUDENT"));
			studentRepository.save(student);
		} else {
			Admin admin = new Admin(username, firstname, lastname, email, encodePassword(password), true);
			admin.getRoleList().add(roleRepository.findByRolename("ROLE_ADMIN"));
			adminRepository.save(admin);
		}

		return "redirect:/";

	}

}
