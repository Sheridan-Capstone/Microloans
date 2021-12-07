package ca.sheridancollege.liuzhun.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.liuzhun.beans.Donation;

public interface DonationRepository extends JpaRepository <Donation, Long>  {

	public List<Donation> findByDonorEmail(String email);
	public List<Donation> findByStudentEmail(String email);
}
