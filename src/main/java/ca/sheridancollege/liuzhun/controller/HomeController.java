package ca.sheridancollege.liuzhun.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.liuzhun.beans.Admin;
import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.repositories.RoleRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;
import ca.sheridancollege.liuzhun.repositories.AdminRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class HomeController {

	private DonorRepository donorRepository;
	private StudentRepository studentRepository;
	private AdminRepository adminRepository;
	private RoleRepository roleRepository;

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@GetMapping("/")
	public String index() {
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
			return "secure/donor";
		} else {
			return "secure/index";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/permissiondenied")
	public String permissiondenied() {
		return "error/permissiondenied";
	}

	@GetMapping("/users/check")
	public String check() {
		return "users/check";
	}

	@GetMapping("/register")
	public String goRegistration() {

		return "register";

	}

	@PostMapping("/register")
	public String doRegistration(@RequestParam String username, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String email, @RequestParam String password, @RequestParam String account) {

		if (account.equals("donor")) {;
			Donor donor = new Donor(username, firstname, lastname, email, encodePassword(password), true);
			donor.getRoleList().add(roleRepository.findByRolename("ROLE_DONOR"));
			donorRepository.save(donor);
		} else if (account.equals("student")) {
			Student student = new Student(username, firstname, lastname, email, encodePassword(password), true);
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
