package esdi.Services.services.implement;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.enums.UserType;
import esdi.Services.models.users.Admin;
import esdi.Services.repositories.AdminRepository;
import esdi.Services.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

//    @Override
//    public AdminDTO getAdminDTO(Long id) {
//        return adminRepository.find
//    }

    @Override
    public Admin getAdminByID(Long id) {
        return getAdminByID(id);
    }

    @Override
    public Admin getAdminByDNI(String dni) {
        return adminRepository.findByDni(dni);
    }

    @Override
    public Admin getAdminByUserName(String user) {
        return adminRepository.findByUser(user);
    }

    @Override
    public void saveAdmin(AdminDTO adminDTO) {
        adminRepository.save(new Admin(adminDTO.getDni(),adminDTO.getFirstName(),adminDTO.getLastName(), adminDTO.getEmail(), adminDTO.getUser(), adminDTO.getPassword(), UserType.ADMIN));
    }

    @Override
    public void saveChanges(Admin admin) {
        adminRepository.save(admin);
    }@Override
    public void updateFirstName(Admin admin, String firstName) {
        admin.setFirstName(firstName);
    }
    @Override
    public void updateLastName(Admin admin, String lastName) {
        admin.setLastName(lastName);
    }
    @Override
    public void updateEmail(Admin admin, String email) {
        admin.setEmail(email);
    }
    @Override
    public void updateUserName(Admin admin, String userName) {
        admin.setUser(userName);
    }
    @Override
    public void updatePassword(Admin admin, String password) {
        admin.setPassword(password);
    }

    @Override
    public void delete(Admin admin) {
        admin.setDeleted(true);
        this.saveChanges(admin);
    }

}
