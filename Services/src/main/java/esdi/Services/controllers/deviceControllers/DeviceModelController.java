package esdi.Services.controllers.deviceControllers;

import esdi.Services.dtos.devices.DeviceModelDTO;
import esdi.Services.services.devices.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceModel")
public class DeviceModelController {

   @Autowired
   DeviceModelService deviceModelService;

   @GetMapping()
   ResponseEntity<?> getAllModels(){
        return deviceModelService.allDeviceModel();
    }

   @GetMapping("/{id}")
   ResponseEntity<?> getModelById(@PathVariable Long id){
       return deviceModelService.findById(id);
   }

   @PostMapping()
   ResponseEntity<?> createDeviceModel(@RequestBody DeviceModelDTO deviceModelDTO){
       return deviceModelService.createDeviceModel(deviceModelDTO);
   }

    @PatchMapping("/{id}")
    ResponseEntity<?> renameDeviceModel(@PathVariable Long id, @RequestParam String name){
        return deviceModelService.renameDeviceModel(id, name);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDeviceModel(@PathVariable Long id){
        return deviceModelService.deleteDeviceModel(id);
    }

}

