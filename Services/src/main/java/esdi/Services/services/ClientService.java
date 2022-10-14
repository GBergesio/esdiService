package esdi.Services.services;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.dtos.request.ClientRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    List<ClientDTO> findAllDTO();
    ResponseEntity<?> allClients();

    ResponseEntity<?> getClientById(Long id);

    ResponseEntity<?> getClientByDni(String dni);

    ResponseEntity<?> createNewClient(ClientRequest clientRequest);

    ResponseEntity<?> updateClient(Long id, ClientRequest clientRequest);

    ResponseEntity<?> deleteStaff(Long id);
}
