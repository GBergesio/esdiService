package esdi.Services.services;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.models.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllUsers();

    List<ClientDTO> getClientsDTO();

    ClientDTO getUserDTO(Long id);
//
    Client getUserByID(Long id);

    Client getUserByDNI(String dni);

    void saveClient(ClientDTO clientDTO);

    void saveChanges(Client client);

    void updateFirstName(Client client, String firstName);

    void updateLastName(Client client, String lastName);
}
