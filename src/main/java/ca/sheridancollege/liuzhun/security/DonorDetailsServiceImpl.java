package ca.sheridancollege.liuzhun.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.liuzhun.beans.Role;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;

@Service
public class DonorDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private DonorRepository donorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ca.sheridancollege.liuzhun.beans.Donor donor = donorRepository.findByEmail(username);

		if (donor == null) {
			System.out.println("User not found:" + donor);
			throw new UsernameNotFoundException("User " + donor + " was not found in the database");
		}
		
		if(!donor.getIsActive()) {
			
			throw new LockedException("User " + donor + " has been deactivated");
		}

		// Change the list of the user's roles into a list of GrantedAuthorityList
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		for (Role role : donor.getRoleList()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}

		// Create a Spring Boot â€œUserâ€� contained in a UserDetailsbased on the
		// information above
		UserDetails donorDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				donor.getEmail(), donor.getEncryptedPassword(), grantList);
		return donorDetails;

	}
}
