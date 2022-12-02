package esdi.Services.services;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.dtos.request.ClientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {

    List<ClientDTO> findAllDTO();
    ResponseEntity<?> allClients();
    ResponseEntity<?> getClientsByCompany(Long id);
    ResponseEntity<?> getClientById(Long id);


    ResponseEntity<?> getAllClientsAuth(Authentication authentication);

    ResponseEntity<?> getClientByIdCC(Long id, Authentication authentication);
    ResponseEntity<?> getClientByDni(String dni, Authentication authentication);

    ResponseEntity<?> createNewClient(ClientRequest clientRequest, Authentication authentication);

    ResponseEntity<?> updateClient(Long id, ClientRequest clientRequest, Authentication authentication);

    ResponseEntity<?> deleteClient(Long id, Authentication authentication);
}
