package esdi.Services.services;

import esdi.Services.dtos.request.ClientRequest;
import org.springframework.http.ResponseEntity;

public interface ClientService {

    ResponseEntity<?> getAllClients();

    ResponseEntity<?> getClientById(Long id);

    ResponseEntity<?> getClientByDni(String dni);

    ResponseEntity<?> createNewClient(ClientRequest clientRequest);

    ResponseEntity<?> updateClient(Long id, ClientRequest clientRequest);

    ResponseEntity<?> deleteStaff(Long id);
}
