package esdi.Services.services.implement;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.enums.UserType;
import esdi.Services.models.User;
import esdi.Services.repositories.UserRepository;
import esdi.Services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static esdi.Services.utils.UserUtils.generatePassword;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<ClientDTO> getListUsersDTO() {
        return userRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getUserDTO(Long id) {
        return userRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

    @Override
    public User getUserByDNI(String dni) {
        return userRepository.findByDni(dni);
    }

    @Override
    public User getUserByUserName(String user) {
        return userRepository.findByUser(user);
    }

    @Override
    public void saveUser(ClientDTO clientDTO) {
        userRepository.save(new User(clientDTO.getDni(), clientDTO.getFirstName(), clientDTO.getLastName(), clientDTO.getAddress(), clientDTO.getNeighborhood(), clientDTO.getPhone(), clientDTO.getCellphone(), clientDTO.getEmail(), clientDTO.getDni(), generatePassword(7), UserType.CLIENT));
    }

    @Override
    public void saveAdmin(AdminDTO adminDTO) {
        userRepository.save(new User(adminDTO.getDni(),adminDTO.getFirstName(),adminDTO.getLastName(), adminDTO.getAddress(),adminDTO.getNeighborhood(), adminDTO.getPhone(),adminDTO.getCellphone(), adminDTO.getEmail(), adminDTO.getUser(), adminDTO.getPassword(), UserType.ADMIN));
    }

    @Override
    public void saveTechnician(TechnicianDTO technicianDTO) {
        userRepository.save(new User(technicianDTO.getDni(),technicianDTO.getFirstName(),technicianDTO.getLastName(), technicianDTO.getAddress(),technicianDTO.getNeighborhood(), technicianDTO.getPhone(),technicianDTO.getCellphone(), technicianDTO.getEmail(), technicianDTO.getUser(), technicianDTO.getPassword(), UserType.TECHNICIAN));
    }

//    @Override
//    public void saveTechnician(UserDTO userDTO) {
//        userRepository.save(new User(userDTO.getDni(),userDTO.getFirstName(),userDTO.getLastName(), userDTO.getAddress(),userDTO.getNeighborhood(), userDTO.getPhone(),userDTO.getCellphone(), userDTO.getEmail(), userDTO.getUser(), userDTO.getPassword(), UserType.TECHNICIAN));
//    }



    //    @Override
    //    public Client getCurrentUser(Authentication authentication) {
    //        return userRepository.findByEmail(authentication.getName());
    //    }



}
