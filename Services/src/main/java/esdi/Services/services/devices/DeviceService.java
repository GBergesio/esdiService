package esdi.Services.services.devices;

import esdi.Services.dtos.devices.DeviceDTO;
import esdi.Services.models.devices.Device;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceService {
        Device saveDevice(Device device);

        DeviceDTO getDeviceDTO(Device device);

        List<DeviceDTO> findAllDTO();

        ResponseEntity<?> allDevices();
        ResponseEntity<?> findById(Long id);

        ResponseEntity<?> createDevice(DeviceDTO deviceDTO);

    //    ResponseEntity<?> updateDevice(Long id, otros);
    //
    //    ResponseEntity<?> deleteDevice(Long id y la orden);
        //no se puede eliminar si tiene vinculada una orden
}
