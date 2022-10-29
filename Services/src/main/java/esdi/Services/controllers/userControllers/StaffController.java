package esdi.Services.controllers.userControllers;

import esdi.Services.dtos.request.StaffRequest;
import esdi.Services.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping("/current/staffByCompany")
    ResponseEntity<?> getStaffByCurrentCompany(Authentication authentication) {
        return staffService.getAllStaffsByCompany(authentication);
    }

    @PostMapping("/current/create")
    public ResponseEntity<?> createStaffByCurrentCompany(Authentication authentication, @RequestBody StaffRequest staffRequest) {
        return staffService.createStaffByCompany(authentication, staffRequest);
    }

    @PatchMapping("/current/update/{id}")
    public ResponseEntity<?> updateStaffByCurrentCompany(Authentication authentication,@PathVariable Long id, @RequestBody StaffRequest staffRequest) {
        return staffService.updateStaffByCompany(authentication, id, staffRequest);
    }


}
