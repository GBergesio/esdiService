package esdi.Services.controllers.deviceControllers;
import esdi.Services.dtos.request.DeviceRequest;
import esdi.Services.services.devices.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/current/client")
    ResponseEntity<?> getDevicesByClient(Authentication authentication) {
        return deviceService.allDevicesByClient(authentication);
    }

    @GetMapping("/current")
    ResponseEntity<?> getDevicesByCompany(Authentication authentication) {
        return deviceService.allDevicesByCompany(authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> createDevice(@RequestBody DeviceRequest deviceRequest, Authentication authentication) {
        return deviceService.createDevice(deviceRequest, authentication);
    }

    @PatchMapping("/current/id/{id}")
    ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody DeviceRequest deviceRequest, Authentication authentication) {
        return deviceService.updateDevice(id, deviceRequest, authentication);
    }

    @DeleteMapping("current/id/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id, Authentication authentication) {
        return deviceService.deleteDevice(id, authentication);
    }

//    Hacer para proxima version ↓
//    @GetMapping("/client/{client}")
//    ResponseEntity<?> getDevicesByClient(@PathVariable Long client) {return deviceService.findByClient(client);}
//
//    @GetMapping("/category/{idCategory}")
//    ResponseEntity<?> getDevicesByCategory(@PathVariable Long idCategory) {return deviceService.findByCategory(idCategory);}
//
//    @GetMapping("/model/{idModel}")
//    ResponseEntity<?> getDevicesByModel(@PathVariable Long idModel) {return deviceService.findByModel(idModel);}
//
//    @GetMapping("/serial/{serial}")
//    ResponseEntity<?> getDevicesByModel(@PathVariable String serial) {return deviceService.findBySerial(serial);}



}
