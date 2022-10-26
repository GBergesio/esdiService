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

    @GetMapping()
    public ResponseEntity<?> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> getStaffByDni(@PathVariable String dni) {
        return staffService.getStaffByDni(dni);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<?> getStaffByUser(@PathVariable String user) {
        return staffService.getStaffByUser(user);
    }

    @GetMapping("/current/staffs")
    ResponseEntity<?> getStaffByCurrentCompany(Authentication authentication) {
        return staffService.getAllStaffsAuth(authentication);
    }

    @PostMapping()
    public ResponseEntity<?> createStaff(@RequestBody StaffRequest staffRequest) {
        return staffService.createStaff(staffRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable Long id, @RequestBody StaffRequest staffRequest) {
        return staffService.updateStaff(id, staffRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
        return staffService.deleteStaff(id);
    }

}
