package ca.sheridancollege.liuzhun.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.sheridancollege.liuzhun.beans.ChangeProfile;

class StudentControllerTests {
	// A test to check that a student updating their profile is good
	@Test
	void testStudentDashboardProfileManagerGood() {
		ChangeProfile changeProfile = new ChangeProfile();
		changeProfile.setUsername("testUser");
		changeProfile.setFname("Test");
		changeProfile.setLname("User");
		changeProfile.setHighSchoolName("Test HS");
		changeProfile.setPostSecondary("Test College");
		changeProfile.setProgram("Testing");
		changeProfile.setCity("myCity");
		changeProfile.setStory("My Test Story");
		
		assertTrue(changeProfile.getUsername().equals("testUser"), "Incorrect Username");
		assertTrue(changeProfile.getFname().equals("Test"), "Incorrect First Name");
		assertTrue(changeProfile.getLname().equals("User"), "Incorrect Last Name");
		assertTrue(changeProfile.getHighSchoolName().equals("Test HS"), "Incorrect High School Name");
		assertTrue(changeProfile.getPostSecondary().equals("Test College"), "Incorrect Post Secondary Name");
		assertTrue(changeProfile.getProgram().equals("Testing"), "Incorrect Program");
		assertTrue(changeProfile.getCity().equals("myCity"), "Incorrect City");
		assertTrue(changeProfile.getStory().equals("My Test Story"), "Incorrect Story");
	}
	
	// A test to check that a student setting their profile incorrectly with wrong information
	@Test
	void testStudentDashboardProfileManagerBad() {
		ChangeProfile changeProfile1 = new ChangeProfile();
		changeProfile1.setUsername("testUser1");
		changeProfile1.setFname("Test1");
		changeProfile1.setLname("User1");
		changeProfile1.setHighSchoolName("Test2 HS");
		changeProfile1.setPostSecondary("Test1 College");
		changeProfile1.setProgram("Testing1");
		changeProfile1.setCity("myCity1");
		changeProfile1.setStory("Test1 Story");
		
		ChangeProfile changeProfile2 = new ChangeProfile();
		changeProfile2.setUsername("testUser2");
		changeProfile2.setFname("Test2");
		changeProfile2.setLname("User2");
		changeProfile2.setHighSchoolName("Test2 HS");
		changeProfile2.setPostSecondary("Test2 College");
		changeProfile2.setProgram("Testing2");
		changeProfile2.setCity("myCity2");
		changeProfile2.setStory("Test2 Story");
		
		assertFalse(changeProfile2.getUsername().equals("testUser1"), "Correct Username");
		assertFalse(changeProfile2.getFname().equals("Test1"), "Correct First Name");
		assertFalse(changeProfile2.getLname().equals("User1"), "Correct Last Name");
		assertFalse(changeProfile2.getHighSchoolName().equals("Test1 HS"), "Correct High School Name");
		assertFalse(changeProfile2.getPostSecondary().equals("Test1 College"), "Correct Post Secondary Name");
		assertFalse(changeProfile2.getProgram().equals("Testing1"), "Correct Program");
		assertFalse(changeProfile2.getCity().equals("myCity1"), "Correct City");
		assertFalse(changeProfile2.getStory().equals("Test1 Story"), "Correct Story");
	}
	
	// A test to check that a student updating their profile at its boundary
	@Test
	void testStudentDashboardProfileManagerBoundary() {
		ChangeProfile changeProfile = new ChangeProfile();
		changeProfile.setUsername("testUser");
		changeProfile.setFname("Test");
		changeProfile.setLname("User");
		changeProfile.setHighSchoolName("Test HS");
		changeProfile.setPostSecondary("Test College");
		changeProfile.setProgram("Testing");
		changeProfile.setCity("myCity");
		//255 word count
		changeProfile.setStory("Otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otter!!!!!");
		
		assertTrue(changeProfile.getUsername().equals("testUser"), "Incorrect Username");
		assertTrue(changeProfile.getFname().equals("Test"), "Incorrect First Name");
		assertTrue(changeProfile.getLname().equals("User"), "Incorrect Last Name");
		assertTrue(changeProfile.getHighSchoolName().equals("Test HS"), "Incorrect High School Name");
		assertTrue(changeProfile.getPostSecondary().equals("Test College"), "Incorrect Post Secondary Name");
		assertTrue(changeProfile.getProgram().equals("Testing"), "Incorrect Program");
		assertTrue(changeProfile.getCity().equals("myCity"), "Incorrect City");
		assertTrue(changeProfile.getStory().equals("Otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otters otter!!!!!"), "Incorrect Story");
	}
}
