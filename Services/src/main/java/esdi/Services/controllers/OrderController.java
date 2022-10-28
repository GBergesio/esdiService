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

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    ClientService clientService;

    @GetMapping()
    ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        }

    @GetMapping("/id/{id}")
    ResponseEntity<?> getDeviceById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @GetMapping("/current/ordersByClient")
    ResponseEntity<?> getOrdersByClient(Authentication authentication) {
        return orderService.allOrdersByClient(authentication);
    }

    @GetMapping("/current/ordersByCompany")
    ResponseEntity<?> getOrdersByCompany(Authentication authentication) {
        return orderService.allOrdersByCompany(authentication);
    }

    @GetMapping("/current/ordersForStaff")
    ResponseEntity<?> getOrdersByCompanyForStaff(Authentication authentication) {
        return orderService.allOrdersByCompanyForStaff(authentication);
    }

    @GetMapping("/orderNumber/{orderNumber}")
    ResponseEntity<?> getDeviceById(@PathVariable int orderNumber){
        return orderService.findByNumber(orderNumber);
    }

    @Transactional
    @PostMapping()
    ResponseEntity<?> newOrder(@RequestBody OrderRequest orderRequest, @RequestParam String dni, @RequestParam Long idDevice){
        return orderService.createOrder(orderRequest, dni, idDevice);
    }

    @Transactional
    @PatchMapping("/modify")
    ResponseEntity<?> updateOrder(Long idOrder,Long idDevice, Long idTechnician, OrderRequest orderRequest){
        return orderService.updateOrder(idOrder,idDevice, idTechnician, orderRequest);
    }

    @Transactional
    @PatchMapping("/release")
    ResponseEntity<?> releaseOrder(Long idOrder){
        return orderService.releaseOrder(idOrder);
    }

    @Transactional
    @PatchMapping("/switchPriority")
    ResponseEntity<?> switchPriority(Long idOrder){
        return orderService.switchPriority(idOrder);
    }

    @Transactional
    @PatchMapping("/orderFinished")
    ResponseEntity<?> orderFinished(Long idOrder){
        return orderService.orderFinished(idOrder);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }

}
