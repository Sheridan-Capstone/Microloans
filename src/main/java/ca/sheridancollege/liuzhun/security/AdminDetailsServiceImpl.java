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
public class AdminDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ca.sheridancollege.liuzhun.beans.Admin admin = adminRepository.findByEmail(username);

		if (admin == null) {
			System.out.println("User not found:" + admin);
			throw new UsernameNotFoundException("User " + admin + " was not found in the database");
		}

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		for (Role role : admin.getRoleList()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}

		UserDetails adminDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				admin.getEmail(), admin.getEncryptedPassword(), grantList);
		return adminDetails;

	}
}
