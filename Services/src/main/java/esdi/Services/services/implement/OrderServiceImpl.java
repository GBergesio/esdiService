package esdi.Services.services.implement;

import esdi.Services.dtos.OrderDTO;
import esdi.Services.dtos.request.DeviceRequest;
import esdi.Services.dtos.request.OrderRequest;
import esdi.Services.mappers.OrderMapper;
import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Technician;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.repositories.DeviceRepository;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.repositories.TechnicianRepository;
import esdi.Services.services.OrderService;
import esdi.Services.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
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
    TechnicianRepository technicianRepository;

    @Autowired
    OrderMapper orderMapper;

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

        if (orderRequest.getComments().isEmpty()){
            return new ResponseEntity<>("Ingrese detalle",HttpStatus.BAD_REQUEST);
        }

        Order order = new Order(UserUtils.newOrder(), orderRequest.getStatus() ,orderRequest.getPriority(), orderRequest.getOrderType(), LocalDateTime.now(),null,orderRequest.getComments());

        client.addOrder(order);
        order.setDevice(device);

        clientRepository.save(client);
        saveOrder(order);

        return new ResponseEntity<>("Orden creada", HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> updateOrder(Long idOrder, Long idDevice ,Long idTechnician, OrderRequest orderRequest) {
        Order order = orderRepository.findById(idOrder).orElse(null);
        Client client = order.getClient();
        Device deviceDB = deviceRepository.findById(idDevice).orElse(null);
        Technician technician = technicianRepository.findById(idTechnician).orElse(null);

        if(order == null)
            return new ResponseEntity<>("Orden no encontrada | Ingrese numero de orden",HttpStatus.BAD_REQUEST);
        if(order != null){
            order.setDevice(deviceDB);
            order.setClient(client);
            order.setTechnician(technician);
            order.setOrderNumber(order.getOrderNumber());
            order.setOrderType(orderRequest.getOrderType());
            order.setComments(orderRequest.getComments());
            order.setJoinDate(orderRequest.getJoinDate());
            order.setOutDate(orderRequest.getOutDate());
            order.setPriority(orderRequest.getPriority());
            order.setStatus(orderRequest.getStatus());
            orderRepository.save(order);
        }
        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteOrder(Long id) {
        return null;
    }


    @Override
    public void updateTechnician(Order order, Technician technician) {
        order.setTechnician(technician);
    }
}
