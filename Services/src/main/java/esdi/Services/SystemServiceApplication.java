package esdi.Services;

import esdi.Services.enums.*;
import esdi.Services.models.*;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.products.*;
import esdi.Services.models.users.Neighborhood;
import esdi.Services.models.users.Staff;
import esdi.Services.models.users.Client;
import esdi.Services.repositories.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "System service API Doc", version = "0.0", description = "System service API documentation"))
public class SystemServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(SystemServiceApplication.class, args);
        System.out.println("Application started");
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository,StaffRepository staffRepository,
                                      OrderRepository orderRepository, ProductRepository productRepository, IvaRepository ivaRepository,
                                      CategoryRepository categoryRepository, DolarRepository dolarRepository, BrandRepository brandRepository,
                                      ServiceRepository serviceRepository, DeviceModelRepository deviceModelRepository, DeviceCategoryRepository deviceCategoryRepository,
                                      DeviceRepository deviceRepository,NeighborhoodRepository neighborhoodRepository, CommentRepository commentRepository,
                                      BudgetRepository budgetRepository, OptionBudgetRepository optionBudgetRepository, OptionComponentRepository optionComponentRepository) {
        return (args) -> {

            Staff admin = new Staff("001", "Staff", "Administrador", "bergesiog1@gmail.com", "admin1", "admin123", UserType.ADMIN);
            staffRepository.save(admin);

            Neighborhood neighborhood1 = new Neighborhood();
            neighborhood1.setName("Norte");
            neighborhoodRepository.save(neighborhood1);
            Client client = new Client("001", "Cliente", "Bergesio", "Santillan 35", neighborhood1, "", "3547654824", "bergesiog1@gmail.com", "39323158", "cliente123", UserType.CLIENT);
            clientRepository.save(client);

            Client client2 = new Client("002", "Cliente2", "Bergesio2", "Santillan 35", neighborhood1, "", "3814052408", "santiago.aragon.99@gmail.com", "123456789", "cliente123", UserType.CLIENT);
            clientRepository.save(client2);

            Staff technician = new Staff("55", "Tecnico", "Bergesio", "bergesiog1@gmail.com", "tecni1", "tecnico123", UserType.TECHNICIAN);
            staffRepository.save(technician);

            // MARCAS //

            Brand brand1 = new Brand("AMD");
            brandRepository.save(brand1);

            // DEVICES //

            //DEVICES MODELS//

            DeviceModel deviceModel1 = new DeviceModel();
            deviceModel1.setModel("X541HJ");

            DeviceModel deviceModel2 = new DeviceModel();
            deviceModel2.setModel("TUF GAMING 22X");

            DeviceModel deviceModel3 = new DeviceModel();
            deviceModel3.setModel("dy2061la");

            deviceModelRepository.save(deviceModel1);
            deviceModelRepository.save(deviceModel2);
            deviceModelRepository.save(deviceModel3);

            //DEVICES CATEGORIES//

            DeviceCategory deviceCategory1 = new DeviceCategory("PC de escritorio");
            DeviceCategory deviceCategory2 = new DeviceCategory("Notebook");
            DeviceCategory deviceCategory3 = new DeviceCategory("Netbook");
            DeviceCategory deviceCategory4 = new DeviceCategory("Impresora multifuncion");

            deviceCategoryRepository.save(deviceCategory1);
            deviceCategoryRepository.save(deviceCategory2);
            deviceCategoryRepository.save(deviceCategory3);
            deviceCategoryRepository.save(deviceCategory4);

            //DEVICES//
            Device device1 = new Device();
            device1.setBrand(brand1);
            device1.setCategory(deviceCategory2);
            device1.setDescription("Notebook Gamer");
            device1.setModel(deviceModel1);
            device1.setClient(client);
            device1.setSerial("123124DGASDGA");

            deviceRepository.save(device1);

            Device device2 = new Device();
            device2.setBrand(brand1);
            device2.setCategory(deviceCategory1);
            device2.setDescription("PC Gamer");
            device2.setModel(deviceModel2);
            device2.setClient(client2);
            device2.setSerial("blablalba");

            deviceRepository.save(device2);

            Device device3 = new Device();
            device3.setBrand(brand1);
            device3.setCategory(deviceCategory1);
            device3.setDescription("PC Gamer 2");
            device3.setModel(deviceModel2);
            device3.setClient(client);
            device3.setSerial("jujuju");

            deviceRepository.save(device3);


            // ORDENES DE TRABAJO //

            Order order1 = new Order();
            order1.setOrderNumber(12998);
            order1.setStatus(Status.ON_HOLD);
            order1.setOrderType(OrderType.NORMAL);
            order1.setPriority(Priority.MEDIUM);
            order1.setJoinDate(LocalDateTime.now());
            order1.setOutDate(null);
            order1.setPasswordDevice("passwordcito");
            order1.setClient(client);
            order1.setDevice(device1);
            order1.setOrderDetails("Agregar SSD");
            order1.setStaff(technician);
            technician.addOrder(order1);
            orderRepository.save(order1);

            Order order2 = new Order();
            order2.setOrderNumber(12999);
            order2.setStatus(Status.ON_HOLD);
            order2.setOrderType(OrderType.NORMAL);
            order2.setPriority(Priority.HIGH);
            order2.setJoinDate(LocalDateTime.now());
            order2.setOutDate(null);
            order2.setPasswordDevice("passwordcito");
            order2.setOrderDetails("Lenta, limpiar");
            order2.setClient(client2);
            order2.setDevice(device2);
			order2.setStaff(null);
            orderRepository.save(order2);


            clientRepository.save(client);
            clientRepository.save(client2);
			staffRepository.save(technician);

            // COMMENTS //

            Comment comment1 = new Comment();
            comment1.setComment("Comentario 1");
            comment1.setOrder(order1);
            comment1.setActiveUser(technician.getLastName());
            comment1.setIdUser(technician.getId());
            comment1.setDate(LocalDateTime.now());
            comment1.setEdited(false);
            comment1.setDeleted(false);
            commentRepository.save(comment1);

            Comment comment2 = new Comment();
            comment2.setComment("Comentario 2");
            comment2.setOrder(order1);
            comment2.setActiveUser(technician.getLastName());
            comment2.setIdUser(technician.getId());
            comment2.setDate(LocalDateTime.now());
            comment2.setEdited(true);
            comment2.setDeleted(false);
            commentRepository.save(comment2);

            Comment comment3 = new Comment();
            comment3.setComment("Comentario 3");
            comment3.setOrder(order2);
            comment3.setActiveUser(technician.getLastName());
            comment3.setIdUser(technician.getId());
            comment3.setDate(LocalDateTime.now());
            comment3.setEdited(true);
            comment3.setDeleted(true);
            commentRepository.save(comment3);

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
            product1.setDolar(dolar1.getPrice());
            product1.setBrand(brand1);

            productRepository.save(product1);

            Product product2 = new Product();
            product2.setProductNumber("B450M");
            product2.setDescription("Placa madre ASUS B450M");
            product2.setCostPrice(240);
            product2.setSalePrice(14440);
            product2.setUtility(1.20);
            product2.setIva(iva10);
            product2.setCurrency(Currency.DOLAR);
            product2.setCategory(category1);
            product2.setDolar(dolar1.getPrice());
            product2.setBrand(brand1);

            productRepository.save(product2);


            // SERVICE ARTICULO //

            ServiceArt service1 = new ServiceArt();
            service1.setDescription("Servicio tecnico notebook");
            service1.setCategory(category2);
            service1.setCostPrice(2000);
            service1.setUtility(1.3);
            service1.setSalePrice(5400);
            service1.setIva(iva21);

            serviceRepository.save(service1);

            // PRESUPUESTO //
            Budget budget1 = new Budget();
            budget1.setBudgetNumber(123);
            budget1.setOrderNumber(order1.getOrderNumber());
            budget1.setClient(order1.getClient().getFirstName() + " " + order1.getClient().getLastName());
            budget1.setStatusBudget(StatusBudget.APPROVED);
            budget1.setTotal(9455);
            budget1.setOrder(order1);
            budgetRepository.save(budget1);
//
            Budget budget2 = new Budget();
            budget2.setBudgetNumber(124);
            budget2.setOrderNumber(order2.getOrderNumber());
            budget2.setClient(order2.getClient().getFirstName() + " " + order2.getClient().getLastName());
            budget2.setStatusBudget(StatusBudget.ON_HOLD);
            budget2.setTotal(1222);
            budget2.setOrder(order2);
            budgetRepository.save(budget2);

//            Budget budget2 = new Budget();
//            budget2.setBudgetNumber(124);
//            budget2.setOrderNumber(order2.getOrderNumber());
//            budget2.setClient(order2.getClient().getFirstName() + " " + order2.getClient().getLastName());
//            budget2.setStatusBudget(StatusBudget.ON_HOLD);
//            budget2.setTotal(4999);
//            budget2.setOrder(order2);
//
//            budgetRepository.save(budget2);

            // OPTION BUDGETS //
            OptionBudget optionBudget1 = new OptionBudget();

            optionBudget1.setBudget(budget1);
            optionBudget1.setSelected(true);
            optionBudget1.setDeleted(false);
            optionBudgetRepository.save(optionBudget1);

            OptionBudget optionBudget2 = new OptionBudget();
            optionBudget2.setTotal(321);
            optionBudget2.setBudget(budget1);
            optionBudget2.setDeleted(false);
            optionBudgetRepository.save(optionBudget2);

            // OPTION COMPONTENTS //
            OptionComponent optionComponent1 = new OptionComponent();
            optionComponent1.setIdPoS(product1.getId());
            optionComponent1.setTotalPrice(product1.getSalePrice() * 3);
            optionComponent1.setQuantity(3);
            optionComponent1.setOptionBudget(optionBudget1);
            optionComponentRepository.save(optionComponent1);

            OptionComponent optionComponent2 = new OptionComponent();
            optionComponent2.setIdPoS(product2.getId());
            optionComponent2.setTotalPrice(product2.getSalePrice() * 2);
            optionComponent2.setQuantity(2);
            optionComponent2.setOptionBudget(optionBudget1);
            optionComponentRepository.save(optionComponent2);

            OptionComponent optionComponent3 = new OptionComponent();
            optionComponent3.setIdPoS(product2.getId());
            optionComponent3.setTotalPrice(product2.getSalePrice() * 2);
            optionComponent3.setQuantity(2);
            optionComponent3.setOptionBudget(optionBudget2);
            optionComponentRepository.save(optionComponent3);

            optionBudget1.setTotal(optionComponent1.getTotalPrice() + optionComponent2.getTotalPrice());
            optionBudgetRepository.save(optionBudget1);

        };
    }

}
