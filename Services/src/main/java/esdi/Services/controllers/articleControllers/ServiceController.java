package esdi.Services.controllers.articleControllers;

import esdi.Services.dtos.request.ServiceArtRequest;
import esdi.Services.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @PostMapping("/current")
    ResponseEntity<?> newService(Authentication authentication, @RequestBody ServiceArtRequest serviceArtRequest){
        return serviceService.createServiceByCompany(authentication, serviceArtRequest);
    }

    @GetMapping("/current")
    ResponseEntity<?> servicesByCompany(Authentication authentication) {
        return serviceService.allServicesByCompany(authentication);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteServiceByCompany(@PathVariable Long id, Authentication authentication) {
        return serviceService.deleteServiceByCompany(authentication, id );
    }

    @PatchMapping("/current/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody ServiceArtRequest serviceArtRequest, Authentication authentication) {
        return serviceService.updateServiceByCompany(authentication, id, serviceArtRequest);
    }

}

