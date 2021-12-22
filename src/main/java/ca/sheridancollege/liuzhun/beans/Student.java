package ca.sheridancollege.liuzhun.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String username;
	@NonNull
	private String firstname;
	@NonNull
	private String lastname;
	@NonNull
	private String email;
	@NonNull
	private String encryptedPassword;	
	@NonNull
	private Boolean enabled;
	@NonNull
	private String highSchoolName;
	@NonNull
	private String postSecondaryName;
	@NonNull
	private String city;
	@NonNull
	private String fieldOfInterest;
	@NonNull
	private String story;
	
	private int fieldOfInterestId;
	private String schoolType;
	private int highSchoolId;
	private double gpa;
	
	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	private int approval; // 0 is pending, 1 is accepted, 2 is rejected
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> roleList = new ArrayList<Role>();
}
