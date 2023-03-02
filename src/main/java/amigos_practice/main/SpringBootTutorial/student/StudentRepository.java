package amigos_practice.main.SpringBootTutorial.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Student repository.
 *
 * The Data Access Layer.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find student by email optional. Where 'Student' is class name that annotated with @Entity
     *
     * @param email the email
     * @return the optional
     */
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    /*
        OR USE THIS:

        Optional<Student> findStudentByEmail(String email);

        THAT MEANS: SELECT * FROM student WHERE email = ?
     */



}
