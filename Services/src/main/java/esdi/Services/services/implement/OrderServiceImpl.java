package esdi.Services.services.implement;

import esdi.Services.dtos.OrderDTO;
import esdi.Services.models.Order;
import esdi.Services.models.users.Technician;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
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
    public void updateTechnician(Order order, Technician technician) {
        order.setTechnician(technician);
    }


}



//    @Override
//    public Client getUserByUserName(String userName) {
//        return clientRepository.findByUser(userName);
//    }

//        return orderRepository.save(new Order(orderDTO.getOrderNumber(), Status.ON_HOLD,orderDTO.getPriority(), OrderType.NORMAL, LocalDateTime.now(),orderDTO.getOutDate(),orderDTO.getComments()));