package esdi.Services.services.devices;

import esdi.Services.dtos.devices.DeviceModelDTO;
import esdi.Services.models.devices.DeviceModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DeviceModelService {

    DeviceModel saveDeviceModel(DeviceModel deviceModel);

    DeviceModelDTO getDeviceModelDTO(DeviceModel deviceModel);

    List<DeviceModelDTO> findAllDTO();

    ResponseEntity<?> allDeviceModel();
    ResponseEntity<?> allDeviceModelByCompany(Authentication authentication);
    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> createDeviceModel(DeviceModelDTO deviceModelDTO,Authentication authentication);

    ResponseEntity<?> renameDeviceModel(Long id, String name,Authentication authentication);

    ResponseEntity<?> deleteDeviceModel(Long id,Authentication authentication);
}

