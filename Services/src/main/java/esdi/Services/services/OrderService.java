package esdi.Services.services;

import esdi.Services.dtos.OrderDTO;
import esdi.Services.dtos.request.OrderRequest;
import esdi.Services.models.Order;
import esdi.Services.models.users.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);
    OrderDTO getOrderDTO(Order order);
    List<OrderDTO>getAllOrders();
    Order getOrderId(Long id);
    Order getOrderByNumber(int orderNumber);
    void updateTechnician(Order order, Staff technician);
    ResponseEntity<?> allOrders();
    ResponseEntity<?> allOrdersByClient(Authentication authentication);
    ResponseEntity<?> allOrdersByCompany(Authentication authentication);

    ResponseEntity<?> allOrdersByCompanyForStaff(Authentication authentication);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findByNumber(int orderNumber);
    ResponseEntity<?> createOrder(OrderRequest orderRequest, String dni, Long device);
    ResponseEntity<?> updateOrder(Long idOrder,Long idDevice, Long idTechnician, OrderRequest orderRequest);
    ResponseEntity<?> releaseOrder(Long idOrder);
    ResponseEntity<?> switchPriority(Long idOrder);
    ResponseEntity<?> orderFinished(Long idOrder);
    ResponseEntity<?> deleteOrder(Long id);

}