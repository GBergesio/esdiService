package esdi.Services.services;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.models.User;

import java.util.List;

public interface UserService {

    List<ClientDTO> getListUsersDTO();

    ClientDTO getUserDTO(Long id);

    User getUserByDNI(String dni);

    User getUserByUserName(String user);

    void saveUser(ClientDTO clientDTO);

    void saveAdmin(AdminDTO adminDTO);

    void saveTechnician(TechnicianDTO technicianDTO);

//    User getCurrentUser(Authentication authentication);
}
