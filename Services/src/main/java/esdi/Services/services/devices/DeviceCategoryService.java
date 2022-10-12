package esdi.Services.services.devices;

import esdi.Services.dtos.devices.DeviceCategoryDTO;
import esdi.Services.models.devices.DeviceCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceCategoryService {

    DeviceCategory saveDeviceModel(DeviceCategory deviceCategory);

    DeviceCategoryDTO getDeviceModelDTO(DeviceCategory deviceCategory);

    List<DeviceCategoryDTO> findAllDTO();

    ResponseEntity<?> allDeviceCategory();
    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> createDeviceCategory(DeviceCategoryDTO deviceCategoryDTO);

    ResponseEntity<?> renameDeviceCategory(Long id, String name);

    ResponseEntity<?> deleteDeviceCategory(Long id);

}
