package esdi.Services.services.implement.devices;

import esdi.Services.dtos.devices.DeviceDTO;
import esdi.Services.mappers.DeviceMapper;
import esdi.Services.models.devices.Device;
import esdi.Services.repositories.DeviceRepository;
import esdi.Services.services.devices.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public DeviceDTO getDeviceDTO(Device device) {
        return null;
    }

    @Override
    public List<DeviceDTO> findAllDTO() {
        List<Device> allDevices = deviceRepository.findAll();
        return deviceMapper.toDTO(allDevices);
    }

    @Override
    public ResponseEntity<?> allDevices() {
        return null;
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createDevice(DeviceDTO deviceDTO) {
        return null;
    }
}
