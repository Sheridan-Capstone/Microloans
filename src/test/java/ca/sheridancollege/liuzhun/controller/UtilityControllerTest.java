package ca.sheridancollege.liuzhun.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.beans.Student;
import ca.sheridancollege.liuzhun.repositories.DonorRepository;
import ca.sheridancollege.liuzhun.repositories.StudentRepository;

class UtilityControllerTest {

	// A test to check that a student is successfully added to a donor's favourite student list
	@Test
	void testAddToFavouriteGood() {
		Donor donor = new Donor();
		donor.setEmail("test@email.com");

		Student student = new Student();
		student.setFirstname("John");
		
		Student student2 = new Student();
		student.setFirstname("Jack");

		donor.getFavouriteStudents().add(student);
		donor.getFavouriteStudents().add(student2);
		
		assertTrue(donor.getFavouriteStudents().contains(student), "Student doesn't exist");
		assertTrue(donor.getFavouriteStudents().contains(student2), "Student doesn't exist");
	}

	// A bad test case to check that a student that is not added to the favourite list cannot be found
	@Test
	void testAddToFavouriteBad() {
		Donor donor = new Donor();
		donor.setEmail("test@email.com");

		Student student = new Student();
		student.setFirstname("John");

		Student student2 = new Student();
		student.setFirstname("Jack");

		donor.getFavouriteStudents().add(student);

		assertFalse(donor.getFavouriteStudents().contains(student2), "Student exists");
	}

	// A boundary test case to ensure that a single student can be added to the favourite list
	@Test
	void testAddToFavouriteBoundary() {
		Donor donor = new Donor();
		donor.setEmail("test@email.com");

		Student student = new Student();
		student.setFirstname("John");

		donor.getFavouriteStudents().add(student);
		
		assertTrue(donor.getFavouriteStudents().contains(student), "Student doesn't exist");
	}

	//A test case to check that students can be removed from the favourite list
	@Test
	void testRemoveFavouriteGood() {
		Donor donor = new Donor();
		donor.setEmail("test@email.com");

		Student student = new Student();
		student.setFirstname("John");
		
		Student student2 = new Student();
		student.setFirstname("Jack");

		donor.getFavouriteStudents().add(student);
		donor.getFavouriteStudents().add(student2);

		assertTrue(donor.getFavouriteStudents().remove(student), "Student cannot be removed");
		assertTrue(donor.getFavouriteStudents().remove(student2), "Student cannot be removed");
	}

	//A bad test case to ensure that a student that is not on the favourite list cannot be removed
	@Test
	void testRemoveFavouriteBad() {
		Donor donor = new Donor();
		donor.setEmail("test@email.com");

		Student student = new Student();
		student.setFirstname("John");

		Student student2 = new Student();
		student.setFirstname("Jack");

		donor.getFavouriteStudents().add(student);

		assertFalse(donor.getFavouriteStudents().remove(student2), "Student is removed");
	}
	
	//A boundary test case to ensure that the only student in the favourite list is removed
	@Test
	void testRemoveFavouriteBoundary() {
		Donor donor = new Donor();
		donor.setEmail("test@email.com");

		Student student = new Student();
		student.setFirstname("John");

		donor.getFavouriteStudents().add(student);

		assertTrue(donor.getFavouriteStudents().remove(student), "Student cannot be removed");
	}

}
