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
    @GetMapping()
    ResponseEntity<?> getAllUsers() {
        return clientService.allClients();
    }


    @GetMapping("/id/{id}")
    ResponseEntity<?> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/company/{id}")
    ResponseEntity<?> getClientByCompany(@PathVariable Long id) {
        return clientService.getClientsByCompany(id);
    }

    @GetMapping("/current/clients")
    ResponseEntity<?> getClientsByCurrentCompany(Authentication authentication) {
        return clientService.getAllClientsAuth(authentication);
    }
    @GetMapping("/dni/{dni}")
    ResponseEntity<?> getClientByDni(@PathVariable String dni) {
        return clientService.getClientByDni(dni);
    }

    @PostMapping()
    ResponseEntity<?> createNewClient(@RequestBody ClientRequest clientRequest) {
        return clientService.createNewClient(clientRequest);
    }

    @PatchMapping("/id/{id}")
    ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        return clientService.updateClient(id, clientRequest);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }
}