package amigos_practice.main.SpringBootTutorial.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Student service.
 * <p>
 * The Service Layer.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final String STUDENT_WITH_ID_NOT_EXIST = "Student with id %s does not exist";
    private final String EMAIL_TAKEN = "Email taken";

    /**
     * Instantiates a new Student service.
     *
     * @param studentRepository the student repository
     */
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Gets students.
     *
     * @return the students
     */
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    /**
     * Add new student.
     *
     * @param student the student
     */
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException(EMAIL_TAKEN);
        }
        studentRepository.save(student);
    }

    /**
     * Delete student by id.
     *
     * @param studentId the student id
     */
    public void deleteStudentById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(String.format(STUDENT_WITH_ID_NOT_EXIST, studentId));
        }
        studentRepository.deleteById(studentId);
    }

    /**
     * Update student.
     * @Transactional Allows not to use the repository directly, only manipulates existing fields and saves data to the database
     * Updating data with setter (without using jdbc query):
     * - student.setName(name);
     * - student.setEmail(email);
     *
     * @param studentId the student id
     * @param name      the name
     * @param email     the email
     */
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(String.format(STUDENT_WITH_ID_NOT_EXIST, studentId)));
        if (!ObjectUtils.isEmpty(name) && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }


        if (!ObjectUtils.isEmpty(email) && !Objects.equals(email, student.getEmail())) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException(EMAIL_TAKEN);
            }
            student.setEmail(email);
        }
    }
}
