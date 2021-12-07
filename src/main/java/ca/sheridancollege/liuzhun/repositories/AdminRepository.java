package ca.sheridancollege.liuzhun.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.liuzhun.beans.Admin;


public interface AdminRepository extends JpaRepository <Admin, Long> {

	public Admin findByEmail(String email);
	
	
}
