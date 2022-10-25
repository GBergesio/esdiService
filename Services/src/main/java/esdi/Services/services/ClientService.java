package esdi.Services.services;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.dtos.request.ClientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {

    List<ClientDTO> findAllDTO();
    ResponseEntity<?> allClients();
    ResponseEntity<?> getAllClientsAuth(Authentication authentication);
    ResponseEntity<?> getClientById(Long id);

    ResponseEntity<?> getClientByDni(String dni);

    ResponseEntity<?> getClientsByCompany(Long id);

    ResponseEntity<?> createNewClient(ClientRequest clientRequest);

    ResponseEntity<?> updateClient(Long id, ClientRequest clientRequest);

    ResponseEntity<?> deleteClient(Long id);
}
