package polimi.it.smsSofwareEng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan(basePackages = {"polimi"})
@EnableWebMvc
@EnableJpaRepositories
public class SmsSoftwareEngApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsSoftwareEngApplication.class, args);
	}

}
