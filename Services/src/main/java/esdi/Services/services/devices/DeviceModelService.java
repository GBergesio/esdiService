package esdi.Services.services.devices;

import esdi.Services.dtos.devices.DeviceModelDTO;
import esdi.Services.models.devices.DeviceModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceModelService {

    DeviceModel saveDeviceModel(DeviceModel deviceModel);

    DeviceModelDTO getDeviceModelDTO(DeviceModel deviceModel);

    List<DeviceModelDTO> findAllDTO();

    ResponseEntity<?> allDeviceModel();
    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> createDeviceModel(DeviceModelDTO deviceModelDTO);

    ResponseEntity<?> renameDeviceModel(Long id, String name);

    ResponseEntity<?> deleteDeviceModel(Long id);
}

