package esdi.Services.controllers.deviceControllers;

import esdi.Services.dtos.devices.DeviceCategoryDTO;
import esdi.Services.services.devices.DeviceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceCategory")
public class DeviceCategoryController {

    @Autowired
    DeviceCategoryService deviceCategoryService;

    @GetMapping()
    ResponseEntity<?> getAllCategories(){
        return deviceCategoryService.allDeviceCategory();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getCategoryById(@PathVariable Long id){
        return deviceCategoryService.findById(id);
    }

    @PostMapping()
    ResponseEntity<?> createDeviceCategory(@RequestBody DeviceCategoryDTO deviceCategoryDTO){
        return deviceCategoryService.createDeviceCategory(deviceCategoryDTO);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> renameDeviceCategory(@PathVariable Long id, @RequestParam String name){
        return deviceCategoryService.renameDeviceCategory(id, name);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDeviceCategory(@PathVariable Long id){
        return deviceCategoryService.deleteDeviceCategory(id);
    }

}
