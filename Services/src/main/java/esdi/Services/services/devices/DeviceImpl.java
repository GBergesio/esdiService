package esdi.Services.services.devices;
import esdi.Services.dtos.devices.DeviceDTO;
import esdi.Services.dtos.request.DeviceRequest;
import esdi.Services.mappers.DeviceMapper;
import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.products.Brand;
import esdi.Services.models.users.Client;
import esdi.Services.repositories.*;
import esdi.Services.services.devices.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceCategoryRepository deviceCategoryRepository;

    @Autowired
    DeviceModelRepository deviceModelRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public DeviceDTO getDeviceDTO(Device device) {
        return deviceMapper.toDTO(device);
    }

    @Override
    public List<DeviceDTO> findAllDTO() {
        List<Device> allDevices = deviceRepository.findAll();
        return deviceMapper.toDTO(allDevices);
    }

    @Override
    public ResponseEntity<?> allDevices() {
        return new ResponseEntity<>(findAllDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id){
        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (!deviceRepository.existsById(id)){
            return new ResponseEntity<>("No se encontró dispositivo con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(deviceMapper.toDTO(deviceRepository.findById(id).orElse(null)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByCategory(Long idCategory) {

        if(idCategory.equals(null) || idCategory == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        DeviceCategory deviceCategory = deviceCategoryRepository.findById(idCategory).orElse(null);

        if (deviceCategory == null){
            return new ResponseEntity<>("No existe la categoria seleccionada",HttpStatus.BAD_REQUEST);
        }

        List<Device> devices = deviceRepository.findAllByCategory(deviceCategory);

        if (devices.size() == 0){
            return new ResponseEntity<>("No se encontraron dispositivos con la categoria seleccionada",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(deviceMapper.toDTO(devices), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByModel(Long idModel) {

        if(idModel == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        DeviceModel deviceModel = deviceModelRepository.findById(idModel).orElse(null);

        if (deviceModel == null){
            return new ResponseEntity<>("No existe el modelo ingresado",HttpStatus.BAD_REQUEST);
        }

        List<Device> devices = deviceRepository.findAllByModel(deviceModel);

        if (devices.size() == 0){
            return new ResponseEntity<>("No se encontraron dispositivos con el modelo seleccionado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(deviceMapper.toDTO(devices), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findBySerial(String serial) {

        if(serial == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        List<Device> devices = deviceRepository.findAllBySerial(serial);

        if (devices.size() == 0){
            return new ResponseEntity<>("No se encontraron dispositivos con el Serial ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(deviceMapper.toDTO(devices), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByClient(Long idClient) {

        if(idClient == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        Client client = clientRepository.findById(idClient).orElse(null);

        if (client == null){
            return new ResponseEntity<>("No existe el cliente",HttpStatus.BAD_REQUEST);
        }

        List<Device> devices = deviceRepository.findAllByClient(client);

        if (devices.size() == 0){
            return new ResponseEntity<>("No se encontraron dispositivos para el cliente seleccionado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(deviceMapper.toDTO(devices), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createDevice(DeviceRequest deviceRequest) {

        DeviceModel deviceModel = deviceModelRepository.findById(deviceRequest.getModelId()).orElse(null);
        DeviceCategory deviceCategory = deviceCategoryRepository.findById(deviceRequest.getCategoryId()).orElse(null);
        Brand brand = brandRepository.findById(deviceRequest.getBrandId()).orElse(null);
        Client client = clientRepository.findById(deviceRequest.getClientId()).orElse(null);

        if (deviceRequest.getDescription() == null || deviceRequest.getDescription().isEmpty() || deviceRequest.getDescription().isBlank())
            return new ResponseEntity<>("Descripción requerida", HttpStatus.BAD_REQUEST);
        if (deviceRequest.getSerial() == null || deviceRequest.getSerial().isEmpty() || deviceRequest.getSerial().isBlank())
            return new ResponseEntity<>(HttpStatus.OK);
        if(brand == null)
            return new ResponseEntity<>("Marca no encontrada",HttpStatus.BAD_REQUEST);
        if(deviceModel == null)
            return new ResponseEntity<>("Modelo no encontrado",HttpStatus.BAD_REQUEST);
        if(deviceCategory == null)
            return new ResponseEntity<>("Categoria no encontrada",HttpStatus.BAD_REQUEST);
        if(client == null)
            return new ResponseEntity<>("Cliente no encontrado",HttpStatus.BAD_REQUEST);

        Device newDevice = new Device();
        newDevice.setClient(client);
        newDevice.setCategory(deviceCategory);
        newDevice.setBrand(brand);
        newDevice.setModel(deviceModel);
        newDevice.setSerial(deviceRequest.getSerial());
        newDevice.setDescription(deviceRequest.getDescription());
        deviceRepository.save(newDevice);

        return new ResponseEntity<>(deviceMapper.toDTO(newDevice), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateDevice(Long id, DeviceRequest deviceRequest) {
        Device deviceDB = deviceRepository.findById(id).orElse(null);
        DeviceModel deviceModel = deviceModelRepository.findById(deviceRequest.getModelId()).orElse(null);
        DeviceCategory deviceCategory = deviceCategoryRepository.findById(deviceRequest.getCategoryId()).orElse(null);
        Brand brand = brandRepository.findById(deviceRequest.getBrandId()).orElse(null);
        Client client = clientRepository.findById(deviceRequest.getClientId()).orElse(null);

        if(deviceDB == null)
            return new ResponseEntity<>("Dispositivo no encontrado",HttpStatus.BAD_REQUEST);
        if (deviceDB != null){
            deviceDB.setClient(client);
            deviceDB.setCategory(deviceCategory);
            deviceDB.setBrand(brand);
            deviceDB.setModel(deviceModel);
            deviceDB.setSerial(deviceRequest.getSerial());
            deviceDB.setDescription(deviceRequest.getDescription());
            deviceRepository.save(deviceDB);
        }
        return new ResponseEntity<>(deviceMapper.toDTO(deviceDB), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteDevice(Long id) {

        Device deviceDB = deviceRepository.findById(id).orElse(null);

        if(deviceDB == null)
            return new ResponseEntity<>("Dispositivo no encontrado",HttpStatus.BAD_REQUEST);

        List<Order> orders = orderRepository.findAllByDevice(deviceDB);

        if(orders.size() > 0)
            return new ResponseEntity<>("Dispositivo asociado a una orden",HttpStatus.BAD_REQUEST);

        deviceRepository.delete(deviceDB);
        return new ResponseEntity<>("Dispositivo eliminado exitosamente",HttpStatus.OK);
    }

}
