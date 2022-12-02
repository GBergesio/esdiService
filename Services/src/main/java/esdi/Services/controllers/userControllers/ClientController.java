package esdi.Services.controllers.userControllers;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.dtos.request.ClientRequest;
import esdi.Services.models.users.Client;
import esdi.Services.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    //  TODOS LOS CLIENTES
    @GetMapping("/current")
    ResponseEntity<?> getAllClients(Authentication authentication) {
        return clientService.getAllClientsAuth(authentication);
    }

    @GetMapping("/current/id/{id}")
    ResponseEntity<?> getClientById(@PathVariable Long id, Authentication authentication) {
        return clientService.getClientByIdCC(id,authentication);
    }

    @GetMapping("/current/dni/{dni}")
    ResponseEntity<?> getClientByDni(@PathVariable String dni, Authentication authentication) {
        return clientService.getClientByDni(dni,authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> createNewClient(@RequestBody ClientRequest clientRequest, Authentication authentication) {
        return clientService.createNewClient(clientRequest,authentication);
    }

    @PatchMapping("/current/id/{id}")
    ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest,Authentication authentication) {
        return clientService.updateClient(id, clientRequest, authentication);
    }

    @DeleteMapping("/current/id/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id, Authentication authentication) {
        return clientService.deleteClient(id,authentication);
    }

}