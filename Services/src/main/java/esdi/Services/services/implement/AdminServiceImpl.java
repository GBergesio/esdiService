package esdi.Services.services.implement;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.enums.UserType;
import esdi.Services.models.Admin;
import esdi.Services.models.Client;
import esdi.Services.repositories.AdminRepository;
import esdi.Services.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    public Admin getAdminByDNI(String dni) {
        return null;
    }

    @Override
    public Admin getAdminByUserName(String user) {
        return adminRepository.findByUser(user);
    }

    @Override
    public void saveAdmin(AdminDTO adminDTO) {
        adminRepository.save(new Admin(adminDTO.getDni(),adminDTO.getFirstName(),adminDTO.getLastName(), adminDTO.getEmail(), adminDTO.getUser(), adminDTO.getPassword(), UserType.ADMIN));
    }
}
