package esdi.Services.services;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.models.users.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();

    List<ClientDTO> getClientsDTO();

    ClientDTO getUserDTO(Long id);
//
    Client getUserByID(Long id);

    Client getUserByDNI(String dni);

    Client getUserByUserName(String userName);

    void saveClient(ClientDTO clientDTO);

    void saveChanges(Client client);

    void updateFirstName(Client client, String firstName);

    void updateLastName(Client client, String lastName);

    void updateAddress(Client client,String address);
    void updatePhone(Client client,String phone);
    void updateCellPhone(Client client,String cellphone);
    void updateNeighborhood(Client client,String neighborhood);
    void updateEmail(Client client,String email);
    void updateUserName(Client client,String userName);
    void updatePassword(Client client,String password);
}
