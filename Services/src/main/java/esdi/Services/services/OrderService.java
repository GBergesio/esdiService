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
    ResponseEntity<?> allOrders();
    ResponseEntity<?> allOrdersByClient(Authentication authentication);
    ResponseEntity<?> allOrdersByCompany(Authentication authentication);
    ResponseEntity<?> updateTechnician(Order order, Staff technician,Authentication authentication);
    ResponseEntity<?> allOrdersByCompanyForStaff(Authentication authentication);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findByIdAuth(Long id, Authentication authentication);
    ResponseEntity<?> findByNumber(int orderNumber);
    ResponseEntity<?> createOrder(OrderRequest orderRequest, String dni, Long device);
    ResponseEntity<?> updateOrder(Long idOrder,Long idDevice, Long idTechnician, OrderRequest orderRequest);
    ResponseEntity<?> releaseOrder(Authentication authentication, Long id);
    ResponseEntity<?> switchPriority(Authentication authentication, Long idOrder);
    ResponseEntity<?> switchPriorityTemp(Long idOrder);
    ResponseEntity<?> orderFinished(Authentication authentication, Long idOrder);
    ResponseEntity<?> orderStaff(Authentication authentication, Long id, Long idStaff);
    ResponseEntity<?> deleteOrder(Authentication authentication, Long id);

}