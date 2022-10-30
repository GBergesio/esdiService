package esdi.Services.services.devices;

import esdi.Services.dtos.devices.DeviceCategoryDTO;
import esdi.Services.models.devices.DeviceCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DeviceCategoryService {

    DeviceCategory saveDeviceModel(DeviceCategory deviceCategory);

    DeviceCategoryDTO getDeviceModelDTO(DeviceCategory deviceCategory);

    List<DeviceCategoryDTO> findAllDTO();

    ResponseEntity<?> allDeviceCategory();
    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> allDeviceCategoryByCompany(Authentication authentication);

    ResponseEntity<?> createDeviceCategory(DeviceCategoryDTO deviceCategoryDTO, Authentication authentication);

    ResponseEntity<?> renameDeviceCategory(Long id, String name, Authentication authentication);

    ResponseEntity<?> deleteDeviceCategory(Long id, Authentication authentication);

}
