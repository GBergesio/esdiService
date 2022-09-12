package esdi.Services.services.implement;
import esdi.Services.dtos.ServiceDTO;
import esdi.Services.dtos.request.ServiceDTORequest;
import esdi.Services.mappers.ServiceMapper;
import esdi.Services.models.products.Category;
import esdi.Services.models.products.Iva;
import esdi.Services.models.products.ServiceArt;
import esdi.Services.repositories.CategoryRepository;
import esdi.Services.repositories.IvaRepository;
import esdi.Services.repositories.ServiceRepository;
import esdi.Services.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class  ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ServiceMapper serviceMapper;

    @Autowired
    IvaRepository ivaRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public ServiceArt saveService(ServiceArt service) {
        return serviceRepository.save(service);
    }

    @Override
    public ServiceDTO getServiceDTO(ServiceArt service) {
        return serviceMapper.toDTO(service);
    }

    @Override
    public List<ServiceDTO> findAllDTO() {
        List<ServiceArt> allServiceArt = serviceRepository.findAll();
        return serviceMapper.toDTO(allServiceArt);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        ServiceDTO serviceDTO = serviceMapper.toDTO(serviceRepository.findById(id).orElse(null));

        if (serviceDTO == null) {
            return new ResponseEntity<>("No se encontró service con el codigo ingresado", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createService(ServiceDTORequest serviceDTORequest) {
        try {

            Iva iva = ivaRepository.findById(serviceDTORequest.getIvaId()).orElse(null);
            Category category = categoryRepository.findById(serviceDTORequest.getCategoryId()).orElse(null);

            if (iva == null)
                return new ResponseEntity<>("Ingrese Iva", HttpStatus.BAD_REQUEST);

            if (category == null)
                return new ResponseEntity<>("Ingrese categoria", HttpStatus.BAD_REQUEST);

            serviceDTORequest.setSalePrice((serviceDTORequest.getCostPrice() * iva.getIva()) * serviceDTORequest.getUtility());

            ServiceArt service = new ServiceArt();
            service.setDescription(serviceDTORequest.getDescription());
            service.setCostPrice(serviceDTORequest.getCostPrice());
            service.setSalePrice(serviceDTORequest.getSalePrice());
            service.setUtility(serviceDTORequest.getUtility());
            service.setCategory(category);
            service.setIva(iva);

            return new ResponseEntity<>(this.saveService(service), HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
