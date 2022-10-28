package esdi.Services.services;
import esdi.Services.dtos.ServiceDTO;
import esdi.Services.dtos.request.ServiceDTORequest;
import esdi.Services.models.products.ServiceArt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ServiceService {

        ServiceArt saveService(ServiceArt service);
    //
        ServiceDTO getServiceDTO(ServiceArt service);

        List<ServiceDTO> findAllDTO();

        ResponseEntity<?> allServicesByCompany(Authentication authentication);

        ResponseEntity<?> findById(@PathVariable Long id);
    //
        ResponseEntity<?> createService(ServiceDTORequest serviceDTORequest);
    //
    //    ProductDTO saveProductDTO(Product product);
}
