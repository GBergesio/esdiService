package esdi.Services.services.implement;
import esdi.Services.dtos.ServiceArtDTO;
import esdi.Services.dtos.request.ServiceArtRequest;
import esdi.Services.mappers.ServiceMapper;
import esdi.Services.models.products.*;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CategoryRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.IvaRepository;
import esdi.Services.repositories.ServiceRepository;
import esdi.Services.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    CompanyRepository companyRepository;


    @Override
    public ServiceArt saveService(ServiceArt service) {
        return serviceRepository.save(service);
    }

    @Override
    public ServiceArtDTO getServiceDTO(ServiceArt service) {
        return serviceMapper.toDTO(service);
    }

    @Override
    public ResponseEntity<?> deleteService(Long id) {
        ServiceArt serviceArt = serviceRepository.findById(id).orElse(null);

        if(serviceArt == null)
            return new ResponseEntity<>("No se encuentra servicio tecnico con id seleccionado", HttpStatus.BAD_REQUEST);
        else{
            serviceArt.setDeleted(true);
        }
        serviceRepository.save(serviceArt);

        return new ResponseEntity<>("Service eliminado exitosamente",HttpStatus.OK);
    }

    @Override
    public List<ServiceArtDTO> findAllDTO() {
        List<ServiceArt> allServiceArt = serviceRepository.findAll();
        return serviceMapper.toDTO(allServiceArt);
    }

    @Override
    public ResponseEntity<?> allServicesByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<ServiceArt> serviceArts = serviceRepository.findAllByCompany(company);
        List<ServiceArt> serviceAvailables = serviceArts.stream().filter(s -> s.getDeleted().equals(false)).collect(Collectors.toList());

        return new ResponseEntity<>(serviceMapper.toDTO(serviceAvailables), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        ServiceArtDTO serviceArtDTO = serviceMapper.toDTO(serviceRepository.findById(id).orElse(null));

        if (serviceArtDTO == null) {
            return new ResponseEntity<>("No se encontró service con el codigo ingresado", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(serviceArtDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createService(ServiceArtRequest serviceArtRequest) {
        try {

            Iva iva = ivaRepository.findById(serviceArtRequest.getIvaId()).orElse(null);
            Category category = categoryRepository.findById(serviceArtRequest.getCategoryId()).orElse(null);

            if (iva == null)
                return new ResponseEntity<>("Ingrese Iva", HttpStatus.BAD_REQUEST);

            if (category == null)
                return new ResponseEntity<>("Ingrese categoria", HttpStatus.BAD_REQUEST);

            serviceArtRequest.setSalePrice((serviceArtRequest.getCostPrice() * iva.getIva()) * serviceArtRequest.getUtility());

            ServiceArt service = new ServiceArt();
            service.setDescription(serviceArtRequest.getDescription());
            service.setCostPrice(serviceArtRequest.getCostPrice());
            service.setSalePrice(serviceArtRequest.getSalePrice());
            service.setUtility(serviceArtRequest.getUtility());
            service.setCategory(category);
            service.setIva(iva);

            return new ResponseEntity<>(this.saveService(service), HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createServiceByCompany(Authentication authentication, ServiceArtRequest serviceArtRequest) {
        Company company = companyRepository.findByUsername(authentication.getName());

        try {
            Iva iva = ivaRepository.findById(serviceArtRequest.getIvaId()).orElse(null);
            Category category = categoryRepository.findById(serviceArtRequest.getCategoryId()).orElse(null);
            ServiceArt service = new ServiceArt();
            List<ServiceArt> servicesList = serviceRepository.findAllByCompany(company);

            if (iva == null)
                return new ResponseEntity<>("Ingrese Iva", HttpStatus.BAD_REQUEST);

            if (category == null)
                return new ResponseEntity<>("Ingrese categoria", HttpStatus.BAD_REQUEST);

            if(serviceArtRequest.getCostPrice() == 0){
                return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
            }
            if(serviceArtRequest.getCostPrice() > 0){
                service.setCostPrice(serviceArtRequest.getCostPrice());
            }
            if(serviceArtRequest.getSalePrice() == 0){
                return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
            }
            if(serviceArtRequest.getSalePrice() > 0){
                service.setSalePrice(serviceArtRequest.getSalePrice());
            }
            if(serviceArtRequest.getUtility() == 0){
                return new ResponseEntity<>("Ingrese una utilidad mayor a 0",HttpStatus.BAD_REQUEST);
            }
            if(serviceArtRequest.getUtility() > 0){
                service.setUtility(serviceArtRequest.getUtility());
            }
            if (serviceArtRequest.getDescription().isEmpty() || serviceArtRequest.getDescription().isBlank() || serviceArtRequest.getDescription() == null){
                return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
            } else {
                service.setDescription(serviceArtRequest.getDescription());
            }

            service.setIva(iva);
            service.setCategory(category);
            service.setCompany(company);
            service.setDeleted(false);
            serviceRepository.save(service);

            return new ResponseEntity<>("Servicio tecnico creado correctamente", HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateServiceByCompany(Authentication authentication, Long id, ServiceArtRequest serviceArtRequest) {
        Company company = companyRepository.findByUsername(authentication.getName());
        ServiceArt service = serviceRepository.findById(id).orElse(null);
        List<ServiceArt> servicesList = serviceRepository.findAllByCompany(company);
        Iva iva = ivaRepository.findById(serviceArtRequest.getIvaId()).orElse(null);
        Category category = categoryRepository.findById(serviceArtRequest.getCategoryId()).orElse(null);

        if (servicesList.indexOf(service) == -1){
            return new ResponseEntity<>("Servicio técnico no encontrado", HttpStatus.BAD_REQUEST);
        }

        if (serviceArtRequest.getDescription().isEmpty() || serviceArtRequest.getDescription().isBlank() || serviceArtRequest.getDescription() == null){
            return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
        } else {
            service.setDescription(serviceArtRequest.getDescription());
        }

        if(serviceArtRequest.getCostPrice() == 0){
            return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
        }
        if(serviceArtRequest.getCostPrice() > 0){
            service.setCostPrice(serviceArtRequest.getCostPrice());
        }
        if(serviceArtRequest.getSalePrice() == 0){
            return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
        }
        if(serviceArtRequest.getSalePrice() > 0){
            service.setSalePrice(serviceArtRequest.getSalePrice());
        }
        if(serviceArtRequest.getUtility() == 0){
            return new ResponseEntity<>("Ingrese una utilidad mayor a 0",HttpStatus.BAD_REQUEST);
        }
        if(serviceArtRequest.getUtility() > 0){
            service.setUtility(serviceArtRequest.getUtility());
        }
        if(serviceArtRequest.getIvaId().equals(null) || serviceArtRequest.getIvaId() == null){
            return new ResponseEntity<>("Debe seleccionar un valor de IVA para el producto",HttpStatus.BAD_REQUEST);
        }
        if (iva != null){
            if (service.getIva() != iva){
                service.setIva(iva);
            }
        }
        if(serviceArtRequest.getCategoryId().equals(null) || serviceArtRequest.getCategoryId() == null){
            return new ResponseEntity<>("Debe seleccionar una categoria para el producto",HttpStatus.BAD_REQUEST);
        }
        if (category!= null){
            if (service.getCategory() != category){
                service.setCategory(category);
            }
        }

        serviceRepository.save(service);
        return new ResponseEntity<>(serviceMapper.toDTO(service),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteServiceByCompany(Authentication authentication, Long id) {
        ServiceArt serviceArt = serviceRepository.findById(id).orElse(null);
        Company company = companyRepository.findByUsername(authentication.getName());
        List<ServiceArt> serviceArtList = company.getServiceArts();

        if (serviceArtList.indexOf(serviceArt) == -1)
            return new ResponseEntity<>("No se encuentra servicio tecnico con id seleccionado", HttpStatus.BAD_REQUEST);
        else{
            serviceArt.setDeleted(true);
        }
        serviceRepository.save(serviceArt);

        return new ResponseEntity<>("Service eliminado exitosamente",HttpStatus.OK);
    }

}
