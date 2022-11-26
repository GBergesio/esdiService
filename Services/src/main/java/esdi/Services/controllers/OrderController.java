package esdi.Services.controllers;
import esdi.Services.dtos.request.OrderRequest;
import esdi.Services.repositories.StaffRepository;
import esdi.Services.services.ClientService;
import esdi.Services.services.OrderService;
import esdi.Services.services.devices.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping()
    ResponseEntity<?> createOrder(OrderRequest orderRequest, Long idClient, Long device) {
        return orderService.createOrder(orderRequest, idClient, device);
    }

    @GetMapping("/current/forClient")
    ResponseEntity<?> getOrdersByClient(Authentication authentication) {
        return orderService.allOrdersByClient(authentication);
    }

    @GetMapping("/current")
    ResponseEntity<?> getOrdersByCompany(Authentication authentication) {
        return orderService.allOrdersByCompany(authentication);
    }

    @GetMapping("/current/forStaff")
    ResponseEntity<?> getOrdersByCompanyForStaff(Authentication authentication) {
        return orderService.allOrdersByCompanyForStaff(authentication);
    }

    @Transactional
    @PatchMapping("/current/release")
    ResponseEntity<?> releaseOrder(Authentication authentication, Long idOrder){
        return orderService.releaseOrder(authentication ,idOrder);
    }

    @Transactional
    @PatchMapping("/current/switchPriority")
    ResponseEntity<?> switchPriority(Authentication authentication, Long idOrder){
        return orderService.switchPriority(authentication, idOrder);
    }

    @Transactional
    @PatchMapping("/current/finished")
    ResponseEntity<?> orderFinished(Authentication authentication, Long idOrder){
        return orderService.orderFinished(authentication, idOrder);
    }

    @Transactional
    @PatchMapping("/current/orderStaff")
    ResponseEntity<?> orderFinished(Authentication authentication, Long id, Long idStaff){
        return orderService.orderStaff(authentication, id, idStaff);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id, Authentication authentication) {
        return orderService.deleteOrder(authentication ,id);
    }

}
