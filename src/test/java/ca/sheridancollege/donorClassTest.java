package ca.sheridancollege;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;


class donorClassTest {

	@Test
	void testDonationGood() {
		
		Donation d = new Donation();
		d.amount = 100;
		
		assertTrue("Donation amount is invalid", d.amount == 100);
	}
	
	@Test
	void testDonationBad() {
		
		Donation d = new Donation();
		d.amount = -1;
		
		assertTrue("Donation amount is invalid", d.amount == -1);
	}
	
	@Test
	void testDonationBoundary() {
		
		Donation d = new Donation();
		d.amount = 0;
		
		assertTrue("Donation amount is invalid", d.amount == 0);
	}
	
		
	}


class Donation {
	public double amount;
	
}
