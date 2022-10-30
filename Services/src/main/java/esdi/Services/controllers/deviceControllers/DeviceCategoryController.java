package esdi.Services.controllers.deviceControllers;

import esdi.Services.dtos.devices.DeviceCategoryDTO;
import esdi.Services.services.devices.DeviceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceCategory")
public class DeviceCategoryController {

    @Autowired
    DeviceCategoryService deviceCategoryService;

    @GetMapping("/current")
    ResponseEntity<?> getAllCategories(Authentication authentication){
        return deviceCategoryService.allDeviceCategoryByCompany(authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> createDeviceCategory(@RequestBody DeviceCategoryDTO deviceCategoryDTO, Authentication authentication){
        return deviceCategoryService.createDeviceCategory(deviceCategoryDTO, authentication);
    }

    @PatchMapping("/current/{id}")
    ResponseEntity<?> renameDeviceCategory(@PathVariable Long id, @RequestParam String name, Authentication authentication){
        return deviceCategoryService.renameDeviceCategory(id, name, authentication);
    }

    @DeleteMapping("current/{id}")
    ResponseEntity<?> deleteDeviceCategory(@PathVariable Long id, Authentication authentication){
        return deviceCategoryService.deleteDeviceCategory(id, authentication);
    }




}
