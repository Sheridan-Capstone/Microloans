package ca.sheridancollege.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {
	
	

}
