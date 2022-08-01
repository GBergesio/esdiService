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

    void saveChanges(Admin admin);
    void updateFirstName(Admin admin, String firstName);
    void updateLastName(Admin admin, String lastName);
    void updateEmail(Admin admin,String email);
    void updateUserName(Admin admin,String userName);
    void updatePassword(Admin admin,String password);

    void delete(Admin admin);
}
