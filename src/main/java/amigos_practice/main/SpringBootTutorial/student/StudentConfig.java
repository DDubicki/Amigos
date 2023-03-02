package amigos_practice.main.SpringBootTutorial.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student harry = new Student("Harry", "harry.potter@gmail.com", LocalDate.of(1990, Month.JULY, 30));
            Student ron = new Student("Ron", "ron.weasley@gmail.com", LocalDate.of(1990, Month.APRIL, 30));

        studentRepository.saveAll(List.of(harry, ron));

        };
    }
}
