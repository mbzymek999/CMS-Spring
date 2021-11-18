package cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;


@SpringBootApplication
public class Application implements RepositoryRestConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
