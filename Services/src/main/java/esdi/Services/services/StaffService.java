package esdi.Services.services;

import esdi.Services.dtos.request.StaffRequest;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface StaffService {
    ResponseEntity<?> getAllStaffs();

    ResponseEntity<?> getStaffById(Long id);

    ResponseEntity<?> getStaffByDni(String dni);
    ResponseEntity<?> getStaffByUser(String user);

    ResponseEntity<?> createStaff(StaffRequest staffRequest, Long id);

    ResponseEntity<?> updateStaff(Long id, StaffRequest staffRequest);

    ResponseEntity<?> deleteStaff(Long id);

    ///AUTH

    ResponseEntity<?> createStaffByCompany(Authentication authentication, StaffRequest staffRequest);
    ResponseEntity<?> getAllStaffsByCompany(Authentication authentication);
    ResponseEntity<?> updateStaffByCompany(Authentication authentication,Long id, StaffRequest staffRequest);
    Staff getCurrentStaff(Authentication authentication);
}
