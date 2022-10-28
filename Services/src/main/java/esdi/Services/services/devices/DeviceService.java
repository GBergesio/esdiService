package esdi.Services.services.devices;

import esdi.Services.dtos.devices.DeviceDTO;
import esdi.Services.dtos.request.DeviceRequest;
import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.products.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DeviceService {
        Device saveDevice(Device device);
        DeviceDTO getDeviceDTO(Device device);
        List<DeviceDTO> findAllDTO();
        ResponseEntity<?> allDevices();
        ResponseEntity<?> allDevicesByClient(Authentication authentication);
        ResponseEntity<?> findById(Long id);
        ResponseEntity<?> findByCategory(Long category);
        ResponseEntity<?> findByModel(Long idModel);
        ResponseEntity<?> findBySerial(String serial);
        ResponseEntity<?> findByClient(Long idClient);
        ResponseEntity<?> createDevice(DeviceRequest deviceRequest);

        ResponseEntity<?> updateDevice(Long id, DeviceRequest deviceRequest);

        ResponseEntity<?> deleteDevice(Long id);

}
