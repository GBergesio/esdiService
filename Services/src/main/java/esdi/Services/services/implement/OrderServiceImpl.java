package esdi.Services.services.implement;

import esdi.Services.dtos.OrderDTO;
import esdi.Services.dtos.request.OrderRequest;
import esdi.Services.enums.Priority;
import esdi.Services.enums.StatusBudget;
import esdi.Services.mappers.CompanyMapper;
import esdi.Services.mappers.OrderMapper;
import esdi.Services.models.Comment;
import esdi.Services.models.Order;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.*;
import esdi.Services.services.OrderService;
import esdi.Services.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDTO(orderRepository.findAll()) ;
    }

    @Override
    public Order getOrderId(Long id) {
        return null;
    }

    @Override
    public Order getOrderByNumber(int orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrderDTO(Order order) {
        return null;
    }

    @Override
    public ResponseEntity<?> allOrders() {
        return new ResponseEntity<>(getAllOrders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allOrdersByClient(Authentication authentication) {
        Client client = clientRepository.findByUser(authentication.getName());
        List<Order> ordersByClient = orderRepository.findAllByClient(client);

        return new ResponseEntity<>(orderMapper.toDTO(ordersByClient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allOrdersByCompany(Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        List<Order> ordersByCompany = orderRepository.findAllByCompany(company);

        return new ResponseEntity<>(orderMapper.toDTO(ordersByCompany), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTechnician(Order order, Staff technician, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<?> allOrdersByCompanyForStaff(Authentication authentication) {
        Staff staff = staffRepository.findByUser(authentication.getName());
        Company company = companyRepository.findByStaffs(staff);

        List<Order> ordersByCompany = orderRepository.findAllByCompany(company);

        return new ResponseEntity<>(orderMapper.toDTO(ordersByCompany), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (!orderRepository.existsById(id)){
            return new ResponseEntity<>("No se encontró orden con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderMapper.toDTO(orderRepository.findById(id).orElse(null)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByIdAuth(Long id, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<?> findByNumber(int orderNumber) {

        Integer oNumber = orderNumber;
        if(oNumber == null)
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);


        List<Order> order = orderRepository.findAll().stream().filter(order1 -> order1.getOrderNumber() == orderNumber).collect(Collectors.toList());

        if(order.size() == 0){
            return new ResponseEntity<>("No se encontró orden con el número ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);

    }
//    @Transactional
    @Override
    public ResponseEntity<?> createOrder(OrderRequest orderRequest, String dni, Long idDevice) {

        Client client = clientRepository.findByDni(dni);
        Device device = deviceRepository.findById(idDevice).orElse(null);


        if(client == null){
            return new ResponseEntity<>("Cliente no encontrado / Ingrese un cliente",HttpStatus.BAD_REQUEST);
        }

        if(device == null){
            return new ResponseEntity<>("Dispositivo no encontrado / Ingrese un dispositivo",HttpStatus.BAD_REQUEST);
        }

        if (orderRequest.getOrderType().equals(null)){
            return new ResponseEntity<>("Tipo de orden requerido",HttpStatus.BAD_REQUEST);
        }

        if (orderRequest.getStatus().equals(null)){
            return new ResponseEntity<>("Estado de orden requerido",HttpStatus.BAD_REQUEST);
        }

        if (orderRequest.getPriority().equals(null)){
            return new ResponseEntity<>("Tipo de prioridad requerida",HttpStatus.BAD_REQUEST);
        }

        if (orderRequest.getOrderDetails().isEmpty() || orderRequest.getOrderDetails().isBlank()){
            return new ResponseEntity<>("Ingrese detalle",HttpStatus.BAD_REQUEST);
        }

        if (orderRequest.getPasswordDevice().isEmpty() || orderRequest.getPasswordDevice().isBlank() || orderRequest.getPasswordDevice() == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        Order newOrder = new Order();

        newOrder.setOrderNumber(UserUtils.newOrder());
        newOrder.setStatus(orderRequest.getStatus());
        newOrder.setPriority(orderRequest.getPriority());
        newOrder.setOrderType(orderRequest.getOrderType());
        newOrder.setJoinDate(LocalDateTime.now());
        newOrder.setOutDate(null);
        newOrder.setOrderDetails(orderRequest.getOrderDetails());
        newOrder.setStaff(null);
        newOrder.setPasswordDevice(orderRequest.getPasswordDevice());
        client.addOrder(newOrder);
        newOrder.setDevice(device);

        clientRepository.save(client);
        saveOrder(newOrder);

        return new ResponseEntity<>("Orden creada", HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> updateOrder(Long idOrder, Long idDevice ,Long idTechnician, OrderRequest orderRequest) {
        Order order = orderRepository.findById(idOrder).orElse(null);
        Client client = order.getClient();
        Device deviceDB = deviceRepository.findById(idDevice).orElse(null);
        Staff technician = staffRepository.findById(idTechnician).orElse(null);

        if(order == null)
            return new ResponseEntity<>("Orden no encontrada | Ingrese numero de orden",HttpStatus.BAD_REQUEST);
        if(order != null){
            order.setDevice(deviceDB);
            order.setClient(client);
            order.setStaff(technician);
            order.setOrderNumber(order.getOrderNumber());
            order.setOrderType(orderRequest.getOrderType());
            order.setOrderDetails(orderRequest.getOrderDetails());
            order.setPasswordDevice(orderRequest.getPasswordDevice());
            order.setJoinDate(orderRequest.getJoinDate());
            order.setPriority(orderRequest.getPriority());
            order.setStatus(orderRequest.getStatus());

            // solo modificar si tiene presupuesto aprobado y si tiene tecnico asignado ↓
            order.setOutDate(orderRequest.getOutDate());

            orderRepository.save(order);
        }
        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> releaseOrder(Authentication authentication, Long idOrder) {
        Company company = companyRepository.findByUser(authentication.getName());
        Order order = orderRepository.findById(idOrder).orElse(null);
        List<Order> orderList = orderRepository.findAllByCompany(company);

        if (orderList.indexOf(order) == -1)
            return new ResponseEntity<>("No se encuentra Orden", HttpStatus.BAD_REQUEST);
        else{
            order.setStaff(null);
            orderRepository.save(order);
        }
        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> switchPriority(Authentication authentication, Long id) {
        Company company = companyRepository.findByUser(authentication.getName());
        Order order = orderRepository.findById(id).orElse(null);
        List<Order> orderList = orderRepository.findAllByCompany(company);

        if (orderList.indexOf(order) == -1){
            return new ResponseEntity<>("Orden no encontrada | Ingrese numero de orden",HttpStatus.BAD_REQUEST);
        }
        if (orderList.indexOf(order) != -1){
            Priority priority = order.getPriority();
            switch (priority){
                case NORMAL:
                    order.setPriority(Priority.MEDIUM);
                    break;
                case MEDIUM:
                    order.setPriority(Priority.HIGH);
                    break;
                case HIGH:
                    order.setPriority(Priority.EXPRESS);
                    break;
                case EXPRESS:
                    order.setPriority(Priority.NORMAL);
                    break;
            }
            orderRepository.save(order);
        }

        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> orderFinished(Authentication authentication, Long idOrder) {
        Order order = orderRepository.findById(idOrder).orElse(null);
        Company company = companyRepository.findByUser(authentication.getName());
        List<Order> orderList = orderRepository.findAllByCompany(company);

        if (orderList.indexOf(order) == -1){
            return new ResponseEntity<>("Orden no encontrada | Ingrese numero de orden",HttpStatus.BAD_REQUEST);
        }
        if (orderList.indexOf(order) != -1){
            if (order.getStaff() == null){
                return new ResponseEntity<>("Orden sin técnico asignado, por favor asigne uno para poder cambiar el estado de la orden.",HttpStatus.BAD_REQUEST);
            }
            Budget orderBudget = order.getBudget();

            if(orderBudget == null){
                return new ResponseEntity<>("Orden sin presupuesto, por favor cree un presupuesto para cambiar el estado de la orden.",HttpStatus.BAD_REQUEST);
            }

            if(orderBudget.getStatusBudget().equals(StatusBudget.ON_HOLD)){
                return new ResponseEntity<>("Orden con presupuesto sin confirmar, por favor confirme el presupuesto para cambiar el estado de la orden.",HttpStatus.BAD_REQUEST);
            }
        }
        order.setOutDate(LocalDateTime.now());

        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> orderStaff(Authentication authentication, Long id,Long idStaff) {
        Order order = orderRepository.findById(id).orElse(null);
        Company company = companyRepository.findByUser(authentication.getName());
        List<Order> orderList = orderRepository.findAllByCompany(company);
        List<Staff> staffList = staffRepository.findAllByCompany(company);

        if (orderList.indexOf(order) == -1){
            return new ResponseEntity<>("Orden no encontrada | Ingrese numero de orden",HttpStatus.BAD_REQUEST);
        }
        if (orderList.indexOf(order) != -1){
            Staff staff = staffRepository.findById(idStaff).orElse(null);
            if(staffList.indexOf(staff) == -1){
                return new ResponseEntity<>("Staff no encontrado",HttpStatus.BAD_REQUEST);
            }
            if (staffList.indexOf(order) != -1){
                order.setStaff(staff);
            }
        orderRepository.save(order);
        }

        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteOrder(Authentication authentication, Long id) {
        Company company = companyRepository.findByUser(authentication.getName());
        Order orderDB = orderRepository.findById(id).orElse(null);

        if(orderDB == null)
            return new ResponseEntity<>("Orden no encontrada",HttpStatus.BAD_REQUEST);

        if (orderDB.getComments().size() >= 1){
            return new ResponseEntity<>("No se puede eliminar orden porque posee comentarios",HttpStatus.BAD_REQUEST);
        }

        orderDB.setClient(null);
        orderDB.setPriority(null);
        orderDB.setOrderType(null);
        orderDB.setStaff(null);
        orderDB.setStatus(null);
        orderDB.setDevice(null);

        orderRepository.save(orderDB);

        orderRepository.delete(orderDB);
        return new ResponseEntity<>("Orden eliminada exitosamente",HttpStatus.OK);
    }

}