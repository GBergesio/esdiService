package esdi.Services.services.devices;

import esdi.Services.dtos.devices.DeviceModelDTO;
import esdi.Services.mappers.DeviceModelMapper;
import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.products.Product;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.DeviceModelRepository;
import esdi.Services.repositories.DeviceRepository;
import esdi.Services.services.devices.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceModelImpl implements DeviceModelService {

    @Autowired
    DeviceModelRepository deviceModelRepository;
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceModelMapper deviceModelMapper;
    @Autowired
    CompanyRepository companyRepository;

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
    public ResponseEntity<?> allDeviceModelByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<DeviceModel> models = deviceModelRepository.findAllByCompany(company);
        List<DeviceModel> modelsAvailables = models.stream().filter(m -> m.getDeleted().equals(false)).collect(Collectors.toList());

        return new ResponseEntity<>(deviceModelMapper.toDTO(modelsAvailables), HttpStatus.OK);
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
    public ResponseEntity<?> createDeviceModel(DeviceModelDTO deviceModelDTO, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<DeviceModel> allDevices = deviceModelRepository.findAllByCompany(company);
        Boolean modelExists = allDevices.stream().anyMatch(model -> model.getName().equals(deviceModelDTO.getName()));

        try{
            if (deviceModelDTO.getName().equals(null) || deviceModelDTO.getName().isEmpty() || deviceModelDTO.getName().isBlank()){
                return new ResponseEntity<>("Ingrese un nombre para el modelo",HttpStatus.BAD_REQUEST);
            }
            if(modelExists){
                return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
            }

        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setName(deviceModelDTO.getName());
        deviceModel.setDeleted(false);
        deviceModel.setCompany(company);
        deviceModelRepository.save(deviceModel);

        return new ResponseEntity<>("Modelo creado correctamente",HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> renameDeviceModel(Long id, String name, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<DeviceModel> allDevices = deviceModelRepository.findAllByCompany(company);
        Boolean modelExists = allDevices.stream().anyMatch(model -> model.getName().equals(name));
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElse(null);

        if(allDevices.indexOf(deviceModel) == -1){
            return new ResponseEntity<>("No existe el modelo",HttpStatus.BAD_REQUEST);
        }

        if (name.isEmpty() || name.isBlank() || name.equals(null)){
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);
        }

        if(modelExists){
            return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
        }

        deviceModel.setName(name);
        deviceModelRepository.save(deviceModel);

        return new ResponseEntity<>("Modelo renombrado correctamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteDeviceModel(Long id, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        DeviceModel deviceModel = deviceModelRepository.findById(id).orElse(null);
        List<DeviceModel> allDevices = deviceModelRepository.findAllByCompany(company);

        if(allDevices.indexOf(deviceModel) == -1){
            return new ResponseEntity<>("No existe el modelo",HttpStatus.BAD_REQUEST);
        }

        deviceModel.setDeleted(true);
        deviceModelRepository.save(deviceModel);
        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }

}


