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
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Neighborhood;
import esdi.Services.models.users.Staff;
import esdi.Services.models.users.Client;
import esdi.Services.repositories.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.MonthDay;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "System service API Doc", version = "0.0", description = "System service API documentation"))
public class SystemServiceApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(SystemServiceApplication.class, args);
        System.out.println("Application started");
    }

    @Bean
    public CommandLineRunner initData(CompanyRepository companyRepository, ClientRepository clientRepository,StaffRepository staffRepository,
                                      OrderRepository orderRepository, ProductRepository productRepository, IvaRepository ivaRepository,
                                      CategoryRepository categoryRepository, DolarRepository dolarRepository, BrandRepository brandRepository,
                                      ServiceRepository serviceRepository, DeviceModelRepository deviceModelRepository, DeviceCategoryRepository deviceCategoryRepository,
                                      DeviceRepository deviceRepository,NeighborhoodRepository neighborhoodRepository, CommentRepository commentRepository,
                                      BudgetRepository budgetRepository, OptionBudgetRepository optionBudgetRepository, OptionComponentRepository optionComponentRepository) {
        return (args) -> {

            Company ampersand = new Company();
            ampersand.setName("Ampersand Tech");
            ampersand.setCuit("20393231581");
            ampersand.setSector("Software");
            ampersand.setEmail("admin@ampersand.com");
            ampersand.setUsername("ampersandAdmin");
            ampersand.setPassword(passwordEncoder.encode("123"));
            ampersand.setUserType(UserType.SUPERADMIN);
            ampersand.setPlan(CompanyPlan.THREE);
            ampersand.setActive(true);
            companyRepository.save(ampersand);

            Company esdi = new Company();
            esdi.setName("Electro Service");
            esdi.setCuit("20286986669");
            esdi.setSector("Computacion");
            esdi.setEmail("ventas@altaoferta.com");
            esdi.setUsername("C-electro001");
            esdi.setPassword(passwordEncoder.encode("123"));
            esdi.setUserType(UserType.COMPANY);
            esdi.setPlan(CompanyPlan.TWO);
            esdi.setActive(true);
            companyRepository.save(esdi);

            Company tallerChapa = new Company();
            tallerChapa.setName("Taller chapa y pintura");
            tallerChapa.setCuit("2023233133");
            tallerChapa.setSector("Mecanica");
            tallerChapa.setEmail("taller@chapa.com");
            tallerChapa.setUsername("C-taller001");
            tallerChapa.setPassword(passwordEncoder.encode("123"));
            tallerChapa.setUserType(UserType.COMPANY);
            tallerChapa.setPlan(CompanyPlan.ONE);
            tallerChapa.setActive(true);
            companyRepository.save(tallerChapa);

            Staff admin = new Staff("001", "Admin", "admin", "admin@gmail.com", "admin1", passwordEncoder.encode("123"), UserType.ADMIN,false);
            admin.setCompany(esdi);
            staffRepository.save(admin);

            Staff technician = new Staff("55", "Gonza", "Bustamante", "bsmg@tt.com", "electro002tt", passwordEncoder.encode("123"), UserType.TECHNICIAN,false);
            technician.setCompany(esdi);
            staffRepository.save(technician);

            Staff techJavi = new Staff("55", "Javi", "Crespi", "jav@tt.com", "javiC", passwordEncoder.encode("123"), UserType.TECHNICIAN,false);
            techJavi.setCompany(esdi);
            staffRepository.save(techJavi);

            Staff tecnicoAuto = new Staff("5335", "Tecnico", "Autos", "bergesiog1@tt.com", "tecniAuto002tt", passwordEncoder.encode("123"), UserType.TECHNICIAN,false);
            tecnicoAuto.setCompany(tallerChapa);
            staffRepository.save(tecnicoAuto);

            Neighborhood Norte = new Neighborhood();
            Norte.setName("Norte");
            Norte.setCompany(esdi);
            neighborhoodRepository.save(Norte);

            Neighborhood sur = new Neighborhood();
            sur.setName("sur");
            sur.setCompany(esdi);
            neighborhoodRepository.save(sur);

            Client client = new Client("32115620", "Juan", "Perez", "Lv Cordoba 351", Norte, "", "3547654824", "juanpe@gmail.com", "cliente1", passwordEncoder.encode("123"), UserType.CLIENT);
            client.setCompany(esdi);
            clientRepository.save(client);

            Client client2 = new Client("20251523215", "Pedro", "Sanchez", "Liniers 99", sur, "", "3814052408", "pepo.sa.99@gmail.com", "cliente2", passwordEncoder.encode("123"), UserType.CLIENT);
            client2.setCompany(esdi);
            clientRepository.save(client2);

            Client client3 = new Client("002", "pepo 3", "god", "fafaf 35", sur, "", "3814052408", "pepo9@gmail.com", "cliente3", passwordEncoder.encode("123"), UserType.CLIENT);
            client3.setCompany(tallerChapa);
            clientRepository.save(client3);

            Client client4 = new Client("002", "cuscus", "nashe", "lv cordoba 35", Norte, "", "3814052408", "cuscus.99@gmail.com", "cliente4", passwordEncoder.encode("123"), UserType.CLIENT);
            client4.setCompany(tallerChapa);
            clientRepository.save(client4);


            // MARCAS //

            Brand brand1 = new Brand("AMD");
            brand1.setCompany(esdi);
            brand1.setDeleted(false);
            brandRepository.save(brand1);

            Brand brand2 = new Brand("Intel");
            brand2.setCompany(esdi);
            brand2.setDeleted(false);
            brandRepository.save(brand2);

            Brand brand3 = new Brand("Peugeot");
            brand3.setCompany(tallerChapa);
            brand3.setDeleted(false);
            brandRepository.save(brand3);

            // DEVICES //

            //DEVICES MODELS//

            DeviceModel deviceModel1 = new DeviceModel();
            deviceModel1.setModel("X541HJ");
            deviceModel1.setCompany(esdi);
            deviceModel1.setDeleted(false);

            DeviceModel deviceModel2 = new DeviceModel();
            deviceModel2.setModel("TUF GAMING 22X");
            deviceModel2.setCompany(esdi);
            deviceModel2.setDeleted(false);

            DeviceModel deviceModel3 = new DeviceModel();
            deviceModel3.setModel("dy2061la");
            deviceModel3.setCompany(esdi);
            deviceModel3.setDeleted(true);

            DeviceModel deviceModel4 = new DeviceModel();
            deviceModel4.setModel("Corolla 2.0");
            deviceModel4.setCompany(tallerChapa);
            deviceModel4.setDeleted(false);

            deviceModelRepository.save(deviceModel1);
            deviceModelRepository.save(deviceModel2);
            deviceModelRepository.save(deviceModel3);
            deviceModelRepository.save(deviceModel4);
            //DEVICES CATEGORIES//

            DeviceCategory deviceCategory1 = new DeviceCategory("PC de escritorio");
            deviceCategory1.setCompany(esdi);
            deviceCategory1.setDeleted(false);
            DeviceCategory deviceCategory2 = new DeviceCategory("Notebook");
            deviceCategory2.setCompany(esdi);
            deviceCategory2.setDeleted(false);
            DeviceCategory deviceCategory3 = new DeviceCategory("Netbook");
            deviceCategory3.setCompany(esdi);
            deviceCategory3.setDeleted(true);
            DeviceCategory deviceCategory4 = new DeviceCategory("Impresora multifuncion");
            deviceCategory4.setCompany(esdi);
            deviceCategory4.setDeleted(false);
            DeviceCategory deviceCategory5 = new DeviceCategory("Sedan 5 puertas");
            deviceCategory5.setCompany(tallerChapa);
            deviceCategory5.setDeleted(false);

            deviceCategoryRepository.save(deviceCategory1);
            deviceCategoryRepository.save(deviceCategory2);
            deviceCategoryRepository.save(deviceCategory3);
            deviceCategoryRepository.save(deviceCategory4);
            deviceCategoryRepository.save(deviceCategory5);

            //DEVICES//
            Device device1 = new Device();
            device1.setBrand(brand1);
            device1.setCategory(deviceCategory2);
            device1.setDescription("Notebook Gamer");
            device1.setModel(deviceModel1);
            device1.setClient(client);
            device1.setSerial("123124DGASDGA");
            device1.setCompany(esdi);
            device1.setDeleted(false);
            deviceRepository.save(device1);

            Device device2 = new Device();
            device2.setBrand(brand1);
            device2.setCategory(deviceCategory1);
            device2.setDescription("PC Gamer");
            device2.setModel(deviceModel2);
            device2.setClient(client2);
            device2.setSerial("blablalba");
            device2.setCompany(esdi);
            device2.setDeleted(false);

            deviceRepository.save(device2);

            Device device3 = new Device();
            device3.setBrand(brand1);
            device3.setCategory(deviceCategory1);
            device3.setDescription("PC Gamer 2");
            device3.setModel(deviceModel2);
            device3.setClient(client);
            device3.setSerial("jujuju");
            device3.setCompany(esdi);
            device3.setDeleted(false);

            deviceRepository.save(device3);

            Device device4 = new Device();
            device4.setDescription("Peugeot 206");
            device4.setBrand(brand1);
            device4.setCategory(deviceCategory1);
            device4.setClient(client3);
            device4.setModel(deviceModel2);
            device4.setSerial("CDA405");
            device4.setCompany(tallerChapa);
            device4.setDeleted(false);

            deviceRepository.save(device4);

            Device device5 = new Device();
            device5.setDescription("Fiat 500");
            device5.setBrand(brand1);
            device5.setCategory(deviceCategory1);
            device5.setClient(client3);
            device5.setModel(deviceModel2);
            device5.setSerial("AB SAF 21412");
            device5.setCompany(tallerChapa);
            device5.setDeleted(false);

            deviceRepository.save(device5);


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
            order1.setStaff(techJavi);
            order1.setCompany(esdi);
            technician.addOrder(order1);
            orderRepository.save(order1);

            Order order2 = new Order();
            order2.setOrderNumber(12999);
            order2.setStatus(Status.READY_R);
            order2.setOrderType(OrderType.NORMAL);
            order2.setPriority(Priority.NORMAL);
            order2.setJoinDate(LocalDateTime.now().of(2022, 10, 24, 11, 25));
            order2.setOutDate(null);
            order2.setPasswordDevice("passwordcito");
            order2.setOrderDetails("Lenta, limpiar");
            order2.setClient(client2);
            order2.setDevice(device2);
            order2.setCompany(esdi);
			order2.setStaff(techJavi);
            orderRepository.save(order2);

            Order order3 = new Order();
            order3.setOrderNumber(8888);
            order3.setStatus(Status.ON_HOLD);
            order3.setOrderType(OrderType.NORMAL);
            order3.setPriority(Priority.EXPRESS);
            order3.setJoinDate(LocalDateTime.now().minusDays(1));
            order3.setOutDate(null);
            order3.setPasswordDevice("");
            order3.setOrderDetails("Chocado,");
            order3.setClient(client3);
            order3.setCompany(tallerChapa);
            order3.setStaff(tecnicoAuto);
            order3.setDevice(device4);
            orderRepository.save(order3);

            Order order4 = new Order();
            order4.setOrderNumber(5744);
            order4.setStatus(Status.ON_HOLD);
            order4.setOrderType(OrderType.NORMAL);
            order4.setPriority(Priority.HIGH);
            order4.setJoinDate(LocalDateTime.now());
            order4.setOutDate(null);
            order4.setPasswordDevice("AB 123 FC");
            order4.setOrderDetails("cambiar filtros,");
            order4.setClient(client4);
            order4.setCompany(tallerChapa);
            order4.setStaff(tecnicoAuto);
            order4.setDevice(device5);

            orderRepository.save(order4);

            Order order5 = new Order();
            order5.setOrderNumber(41444);
            order5.setStatus(Status.WITHDRAWN_WR);
            order5.setOrderType(OrderType.NORMAL);
            order5.setPriority(Priority.HIGH);
            order5.setJoinDate(LocalDateTime.now().minusDays(3));
            order5.setOutDate(null);
            order5.setPasswordDevice("aggggg");
            order5.setOrderDetails("pantalla rota, limpiar");
            order5.setClient(client2);
            order5.setDevice(device2);
            order5.setCompany(esdi);
            order5.setStaff(technician);
            orderRepository.save(order5);

            Order order6 = new Order();
            order6.setOrderNumber(9988);
            order6.setStatus(Status.WITHDRAWN_WR);
            order6.setOrderType(OrderType.WARRANTY);
            order6.setPriority(Priority.HIGH);
            order6.setJoinDate(LocalDateTime.now().minusDays(1));
            order6.setOutDate(null);
            order6.setPasswordDevice("aggaaaaggg");
            order6.setOrderDetails("limpiar virus");
            order6.setClient(client2);
            order6.setDevice(device2);
            order6.setCompany(esdi);
            order6.setStaff(technician);
            orderRepository.save(order6);

            clientRepository.save(client);
            clientRepository.save(client2);
            clientRepository.save(client3);
            clientRepository.save(client4);
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
            comment1.setCompany(esdi);
            commentRepository.save(comment1);

            Comment comment2 = new Comment();
            comment2.setComment("Comentario 2");
            comment2.setOrder(order1);
            comment2.setActiveUser(technician.getLastName());
            comment2.setIdUser(technician.getId());
            comment2.setDate(LocalDateTime.now());
            comment2.setEdited(true);
            comment2.setDeleted(false);
            comment2.setCompany(esdi);
            commentRepository.save(comment2);

            Comment comment3 = new Comment();
            comment3.setComment("Comentario 3");
            comment3.setOrder(order2);
            comment3.setActiveUser(technician.getLastName());
            comment3.setIdUser(technician.getId());
            comment3.setDate(LocalDateTime.now());
            comment3.setEdited(true);
            comment3.setDeleted(true);
            comment3.setCompany(esdi);
            commentRepository.save(comment3);

            // IVA //

            Iva iva10 = new Iva(1.105);
            Iva iva21 = new Iva(1.21);
            ivaRepository.save(iva10);
            ivaRepository.save(iva21);

            // CATEGORIAS //

            Category category1 = new Category();
            category1.setName("Microprocesadores");
            category1.setCompany(esdi);
            category1.setDeleted(false);
            categoryRepository.save(category1);

            Category category2 = new Category();
            category2.setName("Servicio tecnico PC De Escritorio");
            category2.setCompany(esdi);
            category2.setDeleted(false);
            categoryRepository.save(category2);

            Category category3 = new Category();
            category3.setName("Servicio tecnico Notebooks");
            category3.setCompany(esdi);
            category3.setDeleted(false);
            categoryRepository.save(category3);

            Category category4 = new Category();
            category4.setName("Placas madres");
            category4.setCompany(tallerChapa);
            category4.setDeleted(false);
            categoryRepository.save(category4);


            // DOLAR //

            Dolar dolar1 = new Dolar(160,LocalDateTime.now());
            dolar1.setCompany(esdi);
            dolarRepository.save(dolar1);

            Dolar dolar2 = new Dolar(155,LocalDateTime.now());
            dolar2.setCompany(tallerChapa);
            dolarRepository.save(dolar2);

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
            product1.setCompany(esdi);
            product1.setDeleted(false);

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
            product2.setCompany(esdi);
            product2.setDeleted(false);

            productRepository.save(product2);

            // SERVICE ARTICULO //

            ServiceArt service1 = new ServiceArt();
            service1.setDescription("Servicio tecnico notebook");
            service1.setCategory(category2);
            service1.setCostPrice(2000);
            service1.setUtility(1.3);
            service1.setSalePrice(5400);
            service1.setIva(iva21);
            service1.setCompany(esdi);
            service1.setDeleted(false);

            serviceRepository.save(service1);

            // PRESUPUESTO //
            Budget budget1 = new Budget();
            budget1.setBudgetNumber(123);
            budget1.setOrderNumber(order1.getOrderNumber());
            budget1.setClient(order1.getClient().getFirstName() + " " + order1.getClient().getLastName());
            budget1.setStatusBudget(StatusBudget.ON_HOLD);
            budget1.setTotal(9455);
            budget1.setOrder(order1);
            budget1.setCompany(esdi);
            budgetRepository.save(budget1);
//
            Budget budget2 = new Budget();
            budget2.setBudgetNumber(124);
            budget2.setOrderNumber(order2.getOrderNumber());
            budget2.setClient(order2.getClient().getFirstName() + " " + order2.getClient().getLastName());
            budget2.setStatusBudget(StatusBudget.ON_HOLD);
            budget2.setTotal(1222);
            budget2.setOrder(order2);
            budget2.setCompany(esdi);
            budgetRepository.save(budget2);

            Budget budget3 = new Budget();
            budget3.setBudgetNumber(122224);
            budget3.setOrderNumber(order3.getOrderNumber());
            budget3.setClient(order3.getClient().getFirstName() + " " + order3.getClient().getLastName());
            budget3.setStatusBudget(StatusBudget.APPROVED);
            budget3.setConfirmationDate(LocalDateTime.now());
            budget3.setTotal(1222);
            budget3.setOrder(order3);
            budget3.setCompany(tallerChapa);
            budgetRepository.save(budget3);

            Budget budget4 = new Budget();
            budget4.setBudgetNumber(124);
            budget4.setOrderNumber(order5.getOrderNumber());
            budget4.setClient(order5.getClient().getFirstName() + " " + order5.getClient().getLastName());
            budget4.setStatusBudget(StatusBudget.ON_HOLD);
            budget4.setTotal(1234);
            budget4.setOrder(order5);
            budget4.setCompany(esdi);
            budgetRepository.save(budget4);

//            Budget budget5 = new Budget();
//            budget5.setBudgetNumber(1224);
//            budget5.setOrderNumber(order4.getOrderNumber());
//            budget5.setClient(order4.getClient().getFirstName() + " " + order4.getClient().getLastName());
//            budget5.setStatusBudget(StatusBudget.ON_HOLD);
//            budget5.setTotal(1234);
//            budget5.setOrder(order4);
//            budget5.setCompany(esdi);
//            budgetRepository.save(budget5);

            // OPTION BUDGETS //
            OptionBudget optionBudget1 = new OptionBudget();

            optionBudget1.setBudget(budget1);
            optionBudget1.setSelected(true);
            optionBudget1.setDeleted(false);
            optionBudget1.setCompany(esdi);
            optionBudgetRepository.save(optionBudget1);

            OptionBudget optionBudget2 = new OptionBudget();
            optionBudget2.setTotal(321);
            optionBudget2.setBudget(budget1);
            optionBudget2.setDeleted(false);
            optionBudget2.setCompany(esdi);
            optionBudgetRepository.save(optionBudget2);

            // OPTION COMPONTENTS //
            OptionComponent optionComponent1 = new OptionComponent();
            optionComponent1.setIdPoS(product1.getId());
            optionComponent1.setName("KAKA");
            optionComponent1.setTotalPrice(product1.getSalePrice() * 3);
            optionComponent1.setQuantity(3);
            optionComponent1.setOptionBudget(optionBudget1);
            optionComponent1.setCompany(esdi);
            optionComponentRepository.save(optionComponent1);
//
//            OptionComponent optionComponent2 = new OptionComponent();
//            optionComponent2.setIdPoS(product2.getId());
//            optionComponent2.setName("222222");
//            optionComponent2.setTotalPrice(product2.getSalePrice() * 2);
//            optionComponent2.setQuantity(2);
//            optionComponent2.setOptionBudget(optionBudget1);
//            optionComponent2.setCompany(esdi);
//            optionComponentRepository.save(optionComponent2);
//
//            OptionComponent optionComponent3 = new OptionComponent();
//            optionComponent3.setIdPoS(product2.getId());
//            optionComponent3.setTotalPrice(product2.getSalePrice() * 2);
//            optionComponent3.setQuantity(2);
//            optionComponent3.setOptionBudget(optionBudget2);
//            optionComponent3.setCompany(esdi);
//            optionComponentRepository.save(optionComponent3);
//
//            optionBudget1.setTotal(optionComponent1.getTotalPrice() + optionComponent2.getTotalPrice());
//            optionBudgetRepository.save(optionBudget1);

        };
    }

}
