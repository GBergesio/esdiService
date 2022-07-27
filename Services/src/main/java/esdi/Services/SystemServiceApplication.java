package esdi.Services;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import esdi.Services.models.Admin;
import esdi.Services.models.Client;
import esdi.Services.repositories.AdminRepository;
import esdi.Services.repositories.ClientRepository;
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
	public CommandLineRunner initData(ClientRepository clientRepository, AdminRepository adminRepository) {
		return (args) -> {
			Admin admin = new Admin("00001","Admin","Administrador","bergesiog1@gmail.com","admin1","admin123", UserType.ADMIN);
			adminRepository.save(admin);
			Client client = new Client("39323158","Guille","Bergesio","Santillan 35", Neighborhood.NORTE,"","3547654824","bergesiog1@gmail.com","39323158","cliente123", UserType.CLIENT);
			clientRepository.save(client);
		};
		}

}
