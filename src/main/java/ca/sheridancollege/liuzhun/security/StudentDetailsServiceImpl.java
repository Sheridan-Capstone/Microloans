package ca.sheridancollege.liuzhun.security;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.sheridancollege.liuzhun.beans.Role;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.repositories.AdminRepository;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;

@Service
@Transactional
public class StudentDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Student student = studentRepository.findByEmail(email);

		if (student == null) {
			System.out.println("User not found" + student);
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
	public void updateResetPasswordToken1(String token, String email) throws AccountNotFoundException {
		System.out.println("TEST!!!!!" + email);
		Student student = studentRepository.findByEmail(email);
        System.out.println(email);
        
        if (student != null) {
        	student.setResetPasswordToken(token);
            studentRepository.save(student);
        } else {
            throw new AccountNotFoundException("Could not find any student with the email " + email);
        }
    }
     
    public Student getByResetPasswordToken(String token) {
        return studentRepository.findByResetPasswordToken(token);
    }
     
    public void updatePassword(Student student, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        student.setEncryptedPassword(encodedPassword);
         
        student.setResetPasswordToken(null);
        studentRepository.save(student);
    }
}
