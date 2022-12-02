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

        ResponseEntity<?> findById(Long id);

        //    Hacer para proxima version ↓
//        ResponseEntity<?> findByCategory(Long category);
//        ResponseEntity<?> findByModel(Long idModel);
//        ResponseEntity<?> findBySerial(String serial);
//        ResponseEntity<?> findByClient(Long idClient);



        ResponseEntity<?> allDevicesByClient(Authentication authentication);
        ResponseEntity<?> allDevicesByCompany(Authentication authentication);
        ResponseEntity<?> createDevice(DeviceRequest deviceRequest, Authentication authentication);

        ResponseEntity<?> updateDevice(Long id, DeviceRequest deviceRequest, Authentication authentication);

        ResponseEntity<?> deleteDevice(Long id, Authentication authentication);

}
