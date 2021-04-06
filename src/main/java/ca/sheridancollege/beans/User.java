package ca.sheridancollege.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

	private String uName;
	private String fName;
	private String lName;
	private String password;
	private String cPassword;
	private String pNum;
	private String email;
	private Date dob;
	private String type;
}
