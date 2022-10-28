package esdi.Services.controllers.articleControllers;

import esdi.Services.dtos.request.ServiceDTORequest;
import esdi.Services.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    ResponseEntity<?> newService(@RequestBody ServiceDTORequest serviceDTORequest){
        return serviceService.createService(serviceDTORequest);
    }

    @GetMapping("/current/servicesByCompany")
    ResponseEntity<?> getServicesByCompany(Authentication authentication) {
        return serviceService.allServicesByCompany(authentication);
    }

}
