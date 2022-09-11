package esdi.Services.services;

import esdi.Services.dtos.request.StaffRequest;
import org.springframework.http.ResponseEntity;

public interface StaffService {
    ResponseEntity<?> getAllStaffs();

    ResponseEntity<?> getStaffById(Long id);

    ResponseEntity<?> getStaffByDni(String dni);

    ResponseEntity<?> createStaff(StaffRequest staffRequest);

    ResponseEntity<?> updateStaff(Long id, StaffRequest staffRequest);

    ResponseEntity<?> deleteStaff(Long id);
}
