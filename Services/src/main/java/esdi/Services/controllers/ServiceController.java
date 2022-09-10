package esdi.Services.controllers;

import esdi.Services.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping()
    ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(serviceService.findAllDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getProductBy(@PathVariable Long id){
        return serviceService.findById(id);
    }

}
