package esdi.Services.controllers.deviceControllers;
import esdi.Services.dtos.request.DeviceRequest;
import esdi.Services.services.devices.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping()
    ResponseEntity<?> getAllDevices(){
        return deviceService.allDevices();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getDeviceById(@PathVariable Long id){
        return deviceService.findById(id);
    }

    @GetMapping("/client/{client}")
    ResponseEntity<?> getDevicesByClient(@PathVariable Long client) {return deviceService.findByClient(client);}

    @GetMapping("/category/{idCategory}")
    ResponseEntity<?> getDevicesByCategory(@PathVariable Long idCategory) {return deviceService.findByCategory(idCategory);}

    @GetMapping("/model/{idModel}")
    ResponseEntity<?> getDevicesByModel(@PathVariable Long idModel) {return deviceService.findByModel(idModel);}

    @GetMapping("/serial/{serial}")
    ResponseEntity<?> getDevicesByModel(@PathVariable String serial) {return deviceService.findBySerial(serial);}

    @PostMapping()
    ResponseEntity<?> newDevice(@RequestBody DeviceRequest deviceRequest) {
        return deviceService.createDevice(deviceRequest);
    }

    @PatchMapping("/id/{id}")
    ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody DeviceRequest deviceRequest) {
        return deviceService.updateDevice(id, deviceRequest);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        return deviceService.deleteDevice(id);
    }

}
