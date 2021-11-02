package ca.sheridancollege.liuzhun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.liuzhun.beans.Donor;

public interface DonorRepository extends JpaRepository <Donor, Long> {

	public Donor findByEmail(String email);
	public Donor findByUsername(String username);
	
	
}
