package esdi.Services.services.implement.devices;

import esdi.Services.dtos.devices.DeviceModelDTO;
import esdi.Services.mappers.DeviceModelMapper;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.repositories.DeviceModelRepository;
import esdi.Services.services.devices.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeviceModelImpl implements DeviceModelService {

    @Autowired
    DeviceModelRepository deviceModelRepository;

    @Autowired
    DeviceModelMapper deviceModelMapper;

    @Override
    public DeviceModel saveDeviceModel(DeviceModel deviceModel) {
        return deviceModelRepository.save(deviceModel);
    }

    @Override
    public DeviceModelDTO getDeviceModelDTO(DeviceModel deviceModel) {
        return deviceModelMapper.toDTO(deviceModel);
    }

    @Override
        public List<DeviceModelDTO> findAllDTO() {
            return deviceModelMapper.toDTO(deviceModelRepository.findAll());
        }

    @Override
    public ResponseEntity<?> allDeviceModel() {
        return  new ResponseEntity<>(findAllDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (!deviceModelRepository.existsById(id)){
            return new ResponseEntity<>("No se encontró modelo con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(deviceModelRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createDeviceModel(DeviceModelDTO deviceModelDTO) {

        try{
            if (deviceModelDTO.getModel().equals(null) || deviceModelDTO.getModel().isEmpty() || deviceModelDTO.getModel().isBlank())
                return new ResponseEntity<>("Ingrese un nombre para el modelo",HttpStatus.BAD_REQUEST);

        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setModel(deviceModelDTO.getModel());

        return new ResponseEntity<>(this.saveDeviceModel(deviceModel),HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> renameDeviceModel(Long id, String name) {
        if (name.isEmpty() || name.isBlank() || name.equals(null))
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);

        if (deviceModelRepository.findByModel(name) != null)
            return new ResponseEntity<>("Nombre de categoria en uso",HttpStatus.BAD_REQUEST);

        DeviceModel deviceModel = deviceModelRepository.findById(id).orElse(null);

        if (deviceModel == null)
            return new ResponseEntity<>("Categoria no encontrada",HttpStatus.BAD_REQUEST);

        if (deviceModel.getModel().equals(name))
            return new ResponseEntity<>("Asignar un nombre distinto",HttpStatus.BAD_REQUEST);

        deviceModel.setModel(name);

        return new ResponseEntity<>(this.saveDeviceModel(deviceModel),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteDeviceModel(Long id) {

        DeviceModel deviceModel = deviceModelRepository.findById(id).orElse(null);

        if(deviceModel == null)
            return new ResponseEntity<>("No existe modelo",HttpStatus.BAD_REQUEST);

        deviceModelRepository.delete(deviceModel);
        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }

}


