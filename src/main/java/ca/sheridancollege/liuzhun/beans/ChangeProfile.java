package ca.sheridancollege.liuzhun.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProfile {
	private String username;
	
	private String fname;
	
	private String lname;
	
	private String program;
	
	private String postSecondary;
	
	private String highSchoolName;
	
	private String city;

	private String story;
	
}
