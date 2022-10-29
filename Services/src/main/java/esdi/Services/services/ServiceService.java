package esdi.Services.services;
import esdi.Services.dtos.ServiceArtDTO;
import esdi.Services.dtos.request.ServiceArtRequest;
import esdi.Services.models.products.ServiceArt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ServiceService {

        ServiceArt saveService(ServiceArt service);

        ServiceArtDTO getServiceDTO(ServiceArt service);

        ResponseEntity<?> deleteService(Long id);
        List<ServiceArtDTO> findAllDTO();

        ResponseEntity<?> findById(@PathVariable Long id);

        ResponseEntity<?> createService(ServiceArtRequest serviceArtRequest);
        ResponseEntity<?> allServicesByCompany(Authentication authentication);
        ResponseEntity<?> createServiceByCompany(Authentication authentication, ServiceArtRequest serviceArtRequest);
        ResponseEntity<?> updateServiceByCompany(Authentication authentication,Long id, ServiceArtRequest serviceArtRequest);
        ResponseEntity<?> deleteServiceByCompany(Authentication authentication,Long id);

}
