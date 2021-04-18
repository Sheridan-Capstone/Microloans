package ca.sheridancollege.liuzhun.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.liuzhun.beans.Role;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.repositories.AdminRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;

@Service
public class StudentDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ca.sheridancollege.liuzhun.beans.Student student = studentRepository.findByEmail(username);

		if (student == null) {
			System.out.println("User not found:" + student);
			throw new UsernameNotFoundException("User " + student + " was not found in the database");
		}

		// Change the list of the user's roles into a list of GrantedAuthorityList
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		for (Role role : student.getRoleList()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}

		// Create a Spring Boot “User” contained in a UserDetailsbased on the
		// information above

		UserDetails studentDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				student.getEmail(), student.getEncryptedPassword(), grantList);
		return studentDetails;

	}
}
