package esdi.Services.services.implement.devices;


import esdi.Services.dtos.devices.DeviceCategoryDTO;
import esdi.Services.mappers.DeviceCategoryMapper;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.repositories.DeviceCategoryRepository;
import esdi.Services.services.devices.DeviceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeviceCategoryImpl implements DeviceCategoryService {

    @Autowired
    DeviceCategoryRepository deviceCategoryRepository;

    @Autowired
    DeviceCategoryMapper deviceCategoryMapper;

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
    public ResponseEntity<?> createDeviceCategory(DeviceCategoryDTO deviceCategoryDTO) {
        try{
            if (deviceCategoryDTO.getNameCategory().equals(null) || deviceCategoryDTO.getNameCategory().isEmpty() || deviceCategoryDTO.getNameCategory().isBlank())
                return new ResponseEntity<>("Ingrese un nombre para el modelo",HttpStatus.BAD_REQUEST);

            DeviceCategory deviceCategory = new DeviceCategory();
            deviceCategory.setNameCategory(deviceCategoryDTO.getNameCategory());

            return new ResponseEntity<>(this.saveDeviceModel(deviceCategory),HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> renameDeviceCategory(Long id, String name) {
        if (name.isEmpty() || name.isBlank() || name.equals(null))
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);

        if (deviceCategoryRepository.findByNameCategory(name) != null)
            return new ResponseEntity<>("Nombre de categoria en uso",HttpStatus.BAD_REQUEST);

        DeviceCategory deviceCategory = deviceCategoryRepository.findById(id).orElse(null);

        if (deviceCategory == null)
            return new ResponseEntity<>("Categoria no encontrada",HttpStatus.BAD_REQUEST);

        if (deviceCategory.getNameCategory().equals(name))
            return new ResponseEntity<>("Asignar un nombre distinto",HttpStatus.BAD_REQUEST);

        deviceCategory.setNameCategory(name);

        return new ResponseEntity<>(this.saveDeviceModel(deviceCategory),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteDeviceCategory(Long id) {
        DeviceCategory deviceCategory = deviceCategoryRepository.findById(id).orElse(null);

        if(deviceCategory == null)
            return new ResponseEntity<>("No existe modelo",HttpStatus.BAD_REQUEST);

        deviceCategoryRepository.delete(deviceCategory);
        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }
}
