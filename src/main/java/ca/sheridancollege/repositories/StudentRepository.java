package ca.sheridancollege.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ca.sheridancollege.beans.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
//	public Optional<User> findByType(String type);

	public List<Student> findAll();
}
