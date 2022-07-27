package esdi.Services.controllers;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.models.Client;
import esdi.Services.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

//  TODOS LOS CLIENTES
    @GetMapping("/clients/")
    ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(clientService.getAllUsers(), HttpStatus.OK);
    }

//  TODOS LOS CLIENTES DTO
    @GetMapping("/clientsDTO/")
    ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(clientService.getClientsDTO(), HttpStatus.OK);
    }

//  CLIENTE POR ID
    //agregar otro responseEntity para cuando no haya usuario con ese id
    @GetMapping("/clients/{id}")
    ResponseEntity<?> getUserDTO(@PathVariable Long id){
        return new ResponseEntity<>(clientService.getUserDTO(id), HttpStatus.OK);
    }

//  CLIENTE POR DNI
//agregar otro responseEntity para cuando no haya usuario con ese dni
    @GetMapping("/clients/dni/{dni}")
    public ResponseEntity<?> getUserByDni(@PathVariable String dni) {
        return new ResponseEntity<>(clientService.getUserByDNI(dni), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/clients")
    public ResponseEntity<Object> newClient(@RequestBody ClientDTO clientDTO){

//        if(clientDTO.getEmail().isEmpty()){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
        //que devuelva como mensaje DNI ya existente y el nombre del cliente existente
        if(clientService.getUserByDNI(clientDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido",HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getLastName().isEmpty()){
            return new ResponseEntity<>("Apellido requerido",HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getAddress().isEmpty()){
            return new ResponseEntity<>("Direccion requerida",HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getCellphone().isEmpty()){
            return new ResponseEntity<>("Celular requerido",HttpStatus.FORBIDDEN);
        }
        //me tira error 400
        if (clientDTO.getNeighborhood().equals(null)){
            return new ResponseEntity<>("Barrio requerido",HttpStatus.FORBIDDEN);
        }
        if (clientDTO.getDni().isEmpty()){
            return new ResponseEntity<>("DNI requerido",HttpStatus.FORBIDDEN);
        }
        clientService.saveClient(clientDTO);
        return new ResponseEntity<>("Cliente registrado con éxito", HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/clients/modify")
    public ResponseEntity<?> editClient(
            @RequestParam String dni,
            @RequestParam(required = false) String firstName,@RequestParam(required = false) String lastName,@RequestParam(required = false) String address,
            @RequestParam(required = false) String phone, @RequestParam(required = false) String cellPhone, @RequestParam(required = false) String neighborhood,
            @RequestParam(required = false) String email, @RequestParam(required = false) String userName, @RequestParam(required = false) String password
            ){

        if (dni.isEmpty()){
            return new ResponseEntity<>("No se encontró DNI",HttpStatus.FORBIDDEN);
        }

        Client client = clientService.getUserByDNI(dni);

        if (client == null){
            return new ResponseEntity<>("No se encontró cliente",HttpStatus.FORBIDDEN);
        }

//        if (firstName == null && lastName == null && address == null && phone == null && cellPhone == null && neighborhood == null && email == null && userName == null && password == null){
//            return new ResponseEntity<>("No property was supplied to change", HttpStatus.FORBIDDEN);
//
//        }
// && lastName == null
        if (firstName == null && lastName == null){
            return new ResponseEntity<>("No se ha proporcionado ningun cambio", HttpStatus.FORBIDDEN);
        }

        if (firstName == null){
            return new ResponseEntity<>("No se puede enviar un nombre vacio ", HttpStatus.FORBIDDEN);
        }
//
        if (lastName == null){
            return new ResponseEntity<>("No se puede enviar un apellido vacio", HttpStatus.FORBIDDEN);
        }

        if (firstName != null && !firstName.isEmpty()){
            clientService.updateFirstName(client,firstName);
        }

        if (lastName != null && !lastName.isEmpty()){
            clientService.updateLastName(client,lastName);
        }
//        clientService.updateLastName(client,lastName);
//        clientService.updateFirstName(client,firstName);


        clientService.saveChanges(client);

        return new ResponseEntity<>("Cliente modificado con éxito",HttpStatus.OK);
    }


}
