package esdi.Services.services;

import esdi.Services.dtos.request.StaffRequest;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface StaffService {
    ResponseEntity<?> getAllStaffs();
    ResponseEntity<?> getAllStaffsByCompany(Authentication authentication);
    Staff getCurrentStaff(Authentication authentication);
    ResponseEntity<?> getStaffById(Long id);

    ResponseEntity<?> getStaffByDni(String dni);
    ResponseEntity<?> getStaffByUser(String user);

    ResponseEntity<?> createStaff(StaffRequest staffRequest);

    ResponseEntity<?> updateStaff(Long id, StaffRequest staffRequest);

    ResponseEntity<?> deleteStaff(Long id);
}
