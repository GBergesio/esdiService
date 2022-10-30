package esdi.Services.controllers.deviceControllers;

import esdi.Services.dtos.devices.DeviceModelDTO;
import esdi.Services.services.devices.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceModel")
public class DeviceModelController {

   @Autowired
   DeviceModelService deviceModelService;

   @GetMapping("/current")
   ResponseEntity<?> getAllModels(Authentication authentication){
        return deviceModelService.allDeviceModelByCompany(authentication);
    }


   @PostMapping("/current")
   ResponseEntity<?> createDeviceModel(@RequestBody DeviceModelDTO deviceModelDTO, Authentication authentication){
       return deviceModelService.createDeviceModel(deviceModelDTO, authentication);
   }

    @PatchMapping("/current/{id}")
    ResponseEntity<?> renameDeviceModel(@PathVariable Long id, @RequestParam String name, Authentication authentication){
        return deviceModelService.renameDeviceModel(id, name, authentication);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteDeviceModel(@PathVariable Long id, Authentication authentication){
        return deviceModelService.deleteDeviceModel(id, authentication);
    }

}

