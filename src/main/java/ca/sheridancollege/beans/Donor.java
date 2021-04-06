package ca.sheridancollege.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String username;
	@NonNull
	private String firstname;
	@NonNull
	private String lastname;
	
	private String password;

	private String phone;
	@Email(message = "email invalid")
	private String email;
	
	private Integer donate;

	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="DONOR_STUDENT", joinColumns = @JoinColumn(name="DONOR_ID"), inverseJoinColumns = @JoinColumn(name="STUDENT_ID"))
	private List<Student> studentList= new ArrayList<Student>();

	
	
	


}
