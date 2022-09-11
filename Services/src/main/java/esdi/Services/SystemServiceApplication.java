package esdi.Services;

import esdi.Services.enums.*;
import esdi.Services.models.*;
import esdi.Services.models.products.*;
import esdi.Services.models.users.Staff;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Technician;
import esdi.Services.repositories.*;
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
    public CommandLineRunner initData(ClientRepository clientRepository, StaffRepository adminRepository, TechnicianRepository technicianRepository, OrderRepository orderRepository, ProductRepository productRepository, IvaRepository ivaRepository, CategoryRepository categoryRepository, DolarRepository dolarRepository, BrandRepository brandRepository, ServiceRepository serviceRepository) {
        return (args) -> {

            Staff admin = new Staff("001", "Staff", "Administrador", "bergesiog1@gmail.com", "admin1", "admin123", UserType.ADMIN);
            adminRepository.save(admin);

            Client client = new Client("001", "Cliente", "Bergesio", "Santillan 35", Neighborhood.NORTE, "", "3547654824", "bergesiog1@gmail.com", "39323158", "cliente123", UserType.CLIENT);
            clientRepository.save(client);

            Client client2 = new Client("002", "Cliente2", "Bergesio2", "Santillan 35", Neighborhood.NORTE, "", "3814052408", "santiago.aragon.99@gmail.com", "123456789", "cliente123", UserType.CLIENT);
            clientRepository.save(client2);

            Technician technician = new Technician("55", "Tecnico", "Bergesio", "bergesiog1@gmail.com", "tecni1", "tecnico123", UserType.TECHNICIAN);
            technicianRepository.save(technician);


            // ORDENES DE TRABAJO //

            Order order1 = new Order(4500, Status.ON_HOLD, Priority.NORMAL, OrderType.NORMAL, LocalDateTime.now(), null, "AAA");
            order1.setClient(client);
//			order1.setTechnician(technician);
            orderRepository.save(order1);

            Order order2 = new Order(4501, Status.ON_HOLD, Priority.NORMAL, OrderType.NORMAL, LocalDateTime.now(), null, "AAA");
            order2.setClient(client2);
//			order2.setTechnician(technician);
            orderRepository.save(order2);


            clientRepository.save(client);
            clientRepository.save(client2);
//			technicianRepository.save(technician);

            // IVA //

            Iva iva10 = new Iva(1.105);
            Iva iva21 = new Iva(1.21);
            ivaRepository.save(iva10);
            ivaRepository.save(iva21);

            // CATEGORIAS //

            Category category1 = new Category("Microprocesadores");
            categoryRepository.save(category1);

            Category category2 = new Category("Servicio tecnico PC De Escritorio");
            categoryRepository.save(category2);

            Category category3 = new Category("Servicio tecnico Notebooks");
            categoryRepository.save(category3);


            // DOLAR //

            Dolar dolar1 = new Dolar(145,LocalDateTime.now());
            dolarRepository.save(dolar1);

            // MARCAS //

            Brand brand1 = new Brand("AMD");
            brandRepository.save(brand1);

            // PRODUCTO ARTICULO //

            Product product1 = new Product();
            product1.setProductNumber("R5-5600x");
            product1.setDescription("Microprocesador AMD Ryzen 5 5600x");
            product1.setCostPrice(240);
            product1.setSalePrice(42440);
            product1.setUtility(1.20);
            product1.setIva(iva10);
            product1.setCurrency(Currency.DOLAR);
            product1.setCategory(category1);
            product1.setDolar(dolar1);
            product1.setBrand(brand1);

            productRepository.save(product1);

            // SERVICE ARTICULO //

            ServiceArt service1 = new ServiceArt();
            service1.setDescription("Servicio tecnico notebook");
            service1.setCategory(category2);
            service1.setCostPrice(2000);
            service1.setUtility(1.3);
            service1.setSalePrice(5400);
            service1.setIva(iva21);

            serviceRepository.save(service1);




        };
    }

}
