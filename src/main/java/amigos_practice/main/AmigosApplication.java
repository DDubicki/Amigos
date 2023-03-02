package amigos_practice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO:
 *
 */


/**
 * Run multi instance app by using .jar file.
 * - cd target
 * - java -jar <jar_name>
 * PORT specifying: java -jar <jar_name> --server.port=8081
 */
@SpringBootApplication
public class AmigosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigosApplication.class, args);
	}

}
