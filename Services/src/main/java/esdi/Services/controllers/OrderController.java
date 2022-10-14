package esdi.Services.controllers;

import esdi.Services.dtos.OrderDTO;
import esdi.Services.dtos.request.OrderRequest;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.Order;
import esdi.Services.models.users.Technician;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.repositories.DeviceRepository;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.services.ClientService;
import esdi.Services.services.OrderService;
import esdi.Services.services.TechnicianService;
import esdi.Services.services.devices.DeviceService;
import esdi.Services.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TechnicianService technicianService;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping()
    ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        }

    @GetMapping("/id/{id}")
    ResponseEntity<?> getDeviceById(@PathVariable Long id){
        return orderService.findById(id);
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



//    @Transactional
//    @PatchMapping("/modify")
//    ResponseEntity<Object> newOrder(@RequestParam String orderNumber,@RequestParam(required = false) String technicianName){
//
//        if (orderNumber.isEmpty()){
//            return new ResponseEntity<>("Ingrese numero de orden",HttpStatus.BAD_REQUEST);
//        }
//
//        Order order = orderService.getOrderByNumber(Integer.valueOf(orderNumber));
//        Technician technician = technicianService.getTechUserName(technicianName);
//
//        if (order == null){
//            return new ResponseEntity<>("No se encontró numero de orden",HttpStatus.BAD_REQUEST);
//        }
//
//        if (technician == null){
//            return new ResponseEntity<>("No se encontró tecnico",HttpStatus.BAD_REQUEST);
//        }
//
//        if (technician != null){
//            orderService.updateTechnician(order,technician);
//        }
//
//        orderService.saveOrder(order);
//
//        return new ResponseEntity<>("Orden modificada con éxito",HttpStatus.OK);
//
//    }


}
