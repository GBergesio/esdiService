package esdi.Services;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import esdi.Services.models.User;
import esdi.Services.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SystemServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(SystemServiceApplication.class, args);
		System.out.println("Application started");
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository) {
		return (args) -> {
			User admin = new User("39323158","Guille","Bergesio","Santillan 35", Neighborhood.NORTE,"","3547654824","bergesiog1@gmail.com","guille","cuscus123", UserType.ADMIN);
			userRepository.save(admin);
		};
		}

}
