package esdi.Services.controllers;

import antlr.Utils;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.dtos.OrderDTO;
import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.enums.OrderType;
import esdi.Services.models.Client;
import esdi.Services.models.Order;
import esdi.Services.models.Technician;
import esdi.Services.services.ClientService;
import esdi.Services.services.OrderService;
import esdi.Services.services.TechnicianService;
import esdi.Services.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ClientService clientService;

    @Autowired
    TechnicianService technicianService;

    @GetMapping("/orders/")
    ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        }

    @Transactional
    @PostMapping("/orders")
    ResponseEntity<Object> newOrder(@RequestBody OrderDTO orderDTO, @RequestParam String dni){

        Client client = clientService.getUserByDNI(dni);

        if (orderDTO.getOrderType().equals(null)){
            return new ResponseEntity<>("Tipo de orden requerido",HttpStatus.BAD_REQUEST);
        }

        if (orderDTO.getStatus().equals(null)){
            return new ResponseEntity<>("Estado de orden requerido",HttpStatus.BAD_REQUEST);
        }

        if (orderDTO.getPriority().equals(null)){
            return new ResponseEntity<>("Tipo de prioridad requerida",HttpStatus.BAD_REQUEST);
        }

        if (orderDTO.getComments().isEmpty()){
            return new ResponseEntity<>("Ingrese detalle",HttpStatus.BAD_REQUEST);
        }

        Order order = new Order(UserUtils.newOrder(), orderDTO.getStatus() ,orderDTO.getPriority(), orderDTO.getOrderType(), LocalDateTime.now(),null,orderDTO.getComments());

        client.addOrder(order);

        clientService.saveChanges(client);

        orderService.saveOrder(order);

        return new ResponseEntity<>("Orden creada", HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/orders/modify")
    ResponseEntity<Object> newOrder(@RequestParam String orderNumber,@RequestParam(required = false) String technicianName){

        if (orderNumber.isEmpty()){
            return new ResponseEntity<>("Ingrese numero de orden",HttpStatus.BAD_REQUEST);
        }

        Order order = orderService.getOrderByNumber(Integer.valueOf(orderNumber));
        Technician technician = technicianService.getTechUserName(technicianName);

        if (order == null){
            return new ResponseEntity<>("No se encontró numero de orden",HttpStatus.BAD_REQUEST);
        }

        if (technician == null){
            return new ResponseEntity<>("No se encontró tecnico",HttpStatus.BAD_REQUEST);
        }

        if (technician != null){
            orderService.updateTechnician(order,technician);
        }

        orderService.saveOrder(order);
        //

        return new ResponseEntity<>("Orden modificada con éxito",HttpStatus.OK);

    }


}
