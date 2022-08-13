package esdi.Services;

import esdi.Services.enums.*;
import esdi.Services.models.Admin;
import esdi.Services.models.Client;
import esdi.Services.models.Order;
import esdi.Services.models.Technician;
import esdi.Services.repositories.AdminRepository;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.repositories.TechnicianRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SystemServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(SystemServiceApplication.class, args);
		System.out.println("Application started");
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AdminRepository adminRepository, TechnicianRepository technicianRepository, OrderRepository orderRepository) {
		return (args) -> {

			Admin admin = new Admin("001","Admin","Administrador","bergesiog1@gmail.com","admin1","admin123", UserType.ADMIN);
			adminRepository.save(admin);

			Client client = new Client("001","Cliente","Bergesio","Santillan 35", Neighborhood.NORTE,"","3547654824","bergesiog1@gmail.com","39323158","cliente123", UserType.CLIENT);
			clientRepository.save(client);

			Client client2 = new Client("003","Cliente2","Bergesio2","Santillan 35", Neighborhood.NORTE,"","3547654824","bergesiog1@gmail.com","393231582","cliente123", UserType.CLIENT);
			clientRepository.save(client2);

			Technician technician = new Technician("55","Tecnico","Bergesio","bergesiog1@gmail.com","tecni1","tecnico123", UserType.TECHNICIAN);
			technicianRepository.save(technician);


			// ORDENES DE TRABAJO //

			Order order1 = new Order(4500, Status.ON_HOLD, Priority.NORMAL, OrderType.NORMAL, LocalDateTime.now(),null,"AAA");
			order1.setClient(client);
//			order1.setTechnician(technician);
			orderRepository.save(order1);

			Order order2 = new Order(4501, Status.ON_HOLD, Priority.NORMAL, OrderType.NORMAL, LocalDateTime.now(),null,"AAA");
			order2.setClient(client2);
//			order2.setTechnician(technician);
			orderRepository.save(order2);


			clientRepository.save(client);
			clientRepository.save(client2);
//			technicianRepository.save(technician);
		};
	}

}
