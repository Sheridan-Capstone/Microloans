package ca.sheridancollege;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.sheridancollege.validation.ConstraintValidatorContext;
import ca.sheridancollege.validation.EmailValidator;


public class EmailValidatorTest {
	
	EmailValidator testunit = new EmailValidator();

	@Test
	public void matches( ) {
		boolean emailChk = testunit.validateEmail("jamesli@sheridancollege.ca");
		assertTrue("Unable to Validate Email as proper email", emailChk);
	}

	@Test
	public void matchesOut( ) {
		boolean emailChk = testunit.validateEmail("jameslisheridancollegefhfca");
		assertFalse("Unable to Validate Email as proper email", emailChk);
	}
	@Test
	public void matchesBin( ) {
		boolean emailChk = testunit.validateEmail("i@s.ca");
		assertTrue("Unable to Validate Email as proper email", emailChk);
	}
	
	@Test
	public void matchesBout( ) {
		boolean emailChk = testunit.validateEmail("jamesli@sheridancollege");
		assertFalse("Unable to Validate Email as proper email", emailChk);
	}	
	

}
