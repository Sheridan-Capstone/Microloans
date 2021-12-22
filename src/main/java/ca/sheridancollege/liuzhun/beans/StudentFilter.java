package ca.sheridancollege.liuzhun.beans;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFilter {
	private String schoolType;

	private String highSchool;
	
	private String fieldOfInterest;
	
	private double lowGpa;
	
	private double highGpa;
	
	public List<Student> filter(List<Student> students) {
		return students.
				stream().
				filter(x -> x.getGpa() <= highGpa && 
					   x.getGpa() >= lowGpa && 
					   x.getSchoolType().equalsIgnoreCase(schoolType) &&
					   x.getHighSchoolName().equalsIgnoreCase(highSchool) &&
					   x.getFieldOfInterest().equalsIgnoreCase(fieldOfInterest)
		).collect(Collectors.toList());
	}
}
