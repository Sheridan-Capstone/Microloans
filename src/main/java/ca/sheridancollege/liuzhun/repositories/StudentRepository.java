package ca.sheridancollege.liuzhun.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.liuzhun.beans.Student;

public interface StudentRepository extends JpaRepository <Student, Long> {

	public Student findByEmail(String email);
	
	public List<Student> findBySchoolType(String schoolType);
	
}
