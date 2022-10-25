package esdi.Services.services;

import esdi.Services.dtos.request.StaffRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface StaffService {
    ResponseEntity<?> getAllStaffs();
    ResponseEntity<?> getAllStaffsAuth(Authentication authentication);

    ResponseEntity<?> getStaffById(Long id);

    ResponseEntity<?> getStaffByDni(String dni);

    ResponseEntity<?> createStaff(StaffRequest staffRequest);

    ResponseEntity<?> updateStaff(Long id, StaffRequest staffRequest);

    ResponseEntity<?> deleteStaff(Long id);
}
