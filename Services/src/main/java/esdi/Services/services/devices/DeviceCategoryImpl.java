package esdi.Services.services.devices;


import esdi.Services.dtos.devices.DeviceCategoryDTO;
import esdi.Services.mappers.DeviceCategoryMapper;
import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.DeviceCategoryRepository;
import esdi.Services.repositories.DeviceRepository;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.services.devices.DeviceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceCategoryImpl implements DeviceCategoryService {

    @Autowired
    DeviceCategoryRepository deviceCategoryRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceCategoryMapper deviceCategoryMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public DeviceCategory saveDeviceModel(DeviceCategory deviceCategory) {
        return deviceCategoryRepository.save(deviceCategory);
    }

    @Override
    public DeviceCategoryDTO getDeviceModelDTO(DeviceCategory deviceCategory) {
        return deviceCategoryMapper.toDTO(deviceCategory);
    }

    @Override
    public List<DeviceCategoryDTO> findAllDTO() {
        return deviceCategoryMapper.toDTO(deviceCategoryRepository.findAll());
    }

    @Override
    public ResponseEntity<?> allDeviceCategory() {
        return new ResponseEntity<>(findAllDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allDeviceCategoryByCompany(Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        List<DeviceCategory> categories = deviceCategoryRepository.findAllByCompany(company);
        List<DeviceCategory> categoriesAvailables = categories.stream().filter(category -> category.getDeleted().equals(false)).collect(Collectors.toList());

        return new ResponseEntity<>(deviceCategoryMapper.toDTO(categoriesAvailables), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (!deviceCategoryRepository.existsById(id)){
            return new ResponseEntity<>("No se encontró categoria con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(deviceCategoryRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createDeviceCategory(DeviceCategoryDTO deviceCategoryDTO, Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        List<DeviceCategory> deviceCategories = deviceCategoryRepository.findAllByCompany(company);
        Boolean modelExists = deviceCategories.stream().anyMatch(model -> model.getNameCategory().equals(deviceCategoryDTO.getNameCategory()));

        try{
            if (deviceCategoryDTO.getNameCategory().equals(null) || deviceCategoryDTO.getNameCategory().isEmpty() || deviceCategoryDTO.getNameCategory().isBlank()){
                return new ResponseEntity<>("Ingrese un nombre para el modelo",HttpStatus.BAD_REQUEST);
            }
            if(modelExists){
                return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
            }

            DeviceCategory deviceCategory = new DeviceCategory();
            deviceCategory.setNameCategory(deviceCategoryDTO.getNameCategory());
            deviceCategory.setDeleted(false);
            deviceCategory.setCompany(company);
            deviceCategoryRepository.save(deviceCategory);

            return new ResponseEntity<>("Categoria creada correctamente",HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> renameDeviceCategory(Long id, String name, Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        List<DeviceCategory> deviceCategories = deviceCategoryRepository.findAllByCompany(company);
        Boolean modelExists = deviceCategories.stream().anyMatch(model -> model.getNameCategory().equals(name));

        if (name.isEmpty() || name.isBlank() || name.equals(null))
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);

        DeviceCategory deviceCategory = deviceCategoryRepository.findById(id).orElse(null);
        List<DeviceCategory> categories = deviceCategoryRepository.findAllByCompany(company);

        if(categories.indexOf(deviceCategory) == -1){
            return new ResponseEntity<>("No existe la categoria",HttpStatus.BAD_REQUEST);
        }

        if(modelExists){
            return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
        }

        deviceCategory.setNameCategory(name);
        deviceCategoryRepository.save(deviceCategory);

        return new ResponseEntity<>("Renombrado exitosamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteDeviceCategory(Long id, Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        DeviceCategory deviceCategory = deviceCategoryRepository.findById(id).orElse(null);
        List<DeviceCategory> categories = deviceCategoryRepository.findAllByCompany(company);

        if(categories.indexOf(deviceCategory) == -1){
            return new ResponseEntity<>("No existe la categoria",HttpStatus.BAD_REQUEST);
        }

        deviceCategory.setDeleted(true);
        deviceCategoryRepository.save(deviceCategory);
        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }

}
