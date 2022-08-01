package esdi.Services.services.implement;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.enums.UserType;
import esdi.Services.models.Client;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static esdi.Services.enums.UserType.CLIENT;
import static esdi.Services.utils.UserUtils.generatePassword;


@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList()); }

    @Override
    public Client getUserByDNI(String dni) {return clientRepository.findByDni(dni);}

    @Override
    public Client getUserByUserName(String userName) {
        return clientRepository.findByUser(userName);
    }

    @Override
    public ClientDTO getUserDTO(Long id) {return clientRepository.findById(id).map(ClientDTO::new).orElse(null); }

    @Override
    public Client getUserByID(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
//        if (user =! null){
//            return hacer cuando el cliente se elimine, si es que lo hacemos
//        }
        return null;
    }

    @Override
    public void saveClient(ClientDTO clientDTO) {
        clientRepository.save(new Client(clientDTO.getDni(), clientDTO.getFirstName(), clientDTO.getLastName(), clientDTO.getAddress(), clientDTO.getNeighborhood(), clientDTO.getPhone(), clientDTO.getCellphone(), clientDTO.getEmail(), clientDTO.getDni(), generatePassword(7), CLIENT));
    }

    @Override
    public void saveChanges(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateFirstName(Client client, String firstName) {
        client.setFirstName(firstName);
    }

    @Override
    public void updateLastName(Client client, String lastName) {
        client.setLastName(lastName);
    }

    @Override
    public void updateAddress(Client client, String address) {
        client.setAddress(address);
    }

    @Override
    public void updatePhone(Client client, String phone) {
        client.setPhone(phone);
    }

    @Override
    public void updateCellPhone(Client client, String cellphone) {
        client.setCellphone(cellphone);
    }

    @Override
    public void updateNeighborhood(Client client, String neighborhood) {
    }

    @Override
    public void updateEmail(Client client, String email) {
        client.setEmail(email);
    }

    @Override
    public void updateUserName(Client client, String userName) {
        client.setUser(userName);
    }

    @Override
    public void updatePassword(Client client, String password) {
        client.setPassword(password);
    }


}
