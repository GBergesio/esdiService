package esdi.Services.services;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.models.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> getAllAdmins();

//    AdminDTO getAdminDTO(Long id);

    Admin getAdminByID(Long id);

    Admin getAdminByDNI(String dni);

    Admin getAdminByUserName(String admin);

    void saveAdmin(AdminDTO adminDTO);
}
