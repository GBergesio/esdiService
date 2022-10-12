package esdi.Services.controllers.userControllers;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.models.users.Client;
import esdi.Services.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

//  TODOS LOS CLIENTES DTO
    @GetMapping("")
    ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(clientService.getClientsDTO(), HttpStatus.OK);
    }

//  CLIENTE POR ID
    //agregar otro responseEntity para cuando no haya usuario con ese id
    @GetMapping("/{id}")
    ResponseEntity<?> getUserDTO(@PathVariable Long id){
        return new ResponseEntity<>(clientService.getUserDTO(id), HttpStatus.OK);
    }

//  CLIENTE POR DNI
//agregar otro responseEntity para cuando no haya usuario con ese dni
    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> getUserByDni(@PathVariable String dni) {

        Client client = clientService.getUserByDNI(dni);

        if (client == null){
            return new ResponseEntity<>("No se encontró cliente con el DNI ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(clientService.getUserByDNI(dni), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<Object> newClient(@RequestBody ClientDTO clientDTO){

//        if(clientDTO.getEmail().isEmpty()){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
        //que devuelva como mensaje DNI ya existente y el nombre del cliente existente
        if(clientService.getUserByDNI(clientDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido",HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getLastName().isEmpty()){
            return new ResponseEntity<>("Apellido requerido",HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getAddress().isEmpty()){
            return new ResponseEntity<>("Direccion requerida",HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getCellphone().isEmpty()){
            return new ResponseEntity<>("Celular requerido",HttpStatus.BAD_REQUEST);
        }
        //me tira error 400
        if (clientDTO.getNeighborhood().equals(null)){
            return new ResponseEntity<>("Barrio requerido",HttpStatus.BAD_REQUEST);
        }
        if (clientDTO.getDni().isEmpty()){
            return new ResponseEntity<>("DNI requerido",HttpStatus.BAD_REQUEST);
        }
        clientService.saveClient(clientDTO);
        return new ResponseEntity<>("Cliente registrado con éxito", HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/modify")
    public ResponseEntity<?> editClient(
            @RequestParam String dni,
            @RequestParam(required = false) String firstName
//            , @RequestParam(required = false) String neighborhood
            ,@RequestParam(required = false) String lastName,@RequestParam(required = false) String address,
            @RequestParam(required = false) String phone, @RequestParam(required = false) String cellPhone,
            @RequestParam(required = false) String email, @RequestParam(required = false) String userName, @RequestParam(required = false) String password
            ){

        if (dni.isEmpty()){
            return new ResponseEntity<>("No se encontró DNI",HttpStatus.BAD_REQUEST);
        }

        Client client = clientService.getUserByDNI(dni);

        if (client == null){
            return new ResponseEntity<>("No se encontró cliente",HttpStatus.BAD_REQUEST);
        }

        if (firstName == null && lastName == null && address == null && phone == null && cellPhone == null && email == null && userName == null && password == null){
            return new ResponseEntity<>("No se ha proporcionado ningun cambio", HttpStatus.BAD_REQUEST);
        }
        if (firstName.isEmpty() && lastName.isEmpty() && address.isEmpty() && phone.isEmpty() && cellPhone.isEmpty() && email.isEmpty() && userName.isEmpty() && password.isEmpty()){
            return new ResponseEntity<>("No se ha proporcionado ningun cambio", HttpStatus.FORBIDDEN);
        }
        if (firstName == null){
            return new ResponseEntity<>("No se puede enviar un nombre vacio ", HttpStatus.BAD_REQUEST);
        }
        if (address == null){
            return new ResponseEntity<>("No se puede enviar una direccion vacia", HttpStatus.BAD_REQUEST);
        }
        if (phone == null){
            return new ResponseEntity<>("No se puede enviar un telefono vacio", HttpStatus.BAD_REQUEST);
        }
        if (cellPhone == null){
            return new ResponseEntity<>("No se puede enviar un celular vacio", HttpStatus.BAD_REQUEST);
        }
//        if (neighborhood == null){
//            return new ResponseEntity<>("No se puede enviar un barrio vacio ", HttpStatus.FORBIDDEN);
//        }
        if (email == null){
            return new ResponseEntity<>("No se puede enviar un email vacio", HttpStatus.BAD_REQUEST);
        }
        if (userName == null){
            return new ResponseEntity<>("No se puede enviar un nombre de usuario vacio ", HttpStatus.BAD_REQUEST);
        }
        if (password == null){
            return new ResponseEntity<>("No se puede enviar una contraseña vacia", HttpStatus.BAD_REQUEST);
        }

        if (clientService.getAllClients().stream().filter(client1 -> client1.getUser().equals(userName)).count() > 0){
            return new ResponseEntity<>("nombre de usuario existente", HttpStatus.BAD_REQUEST);
        }

        if (firstName != null && !firstName.isEmpty()){
            clientService.updateFirstName(client,firstName);
        }
        if (lastName != null && !lastName.isEmpty()){
            clientService.updateLastName(client,lastName);
        }
        if (address != null && !address.isEmpty()){
            clientService.updateAddress(client,address);
        }
        if (phone != null && !phone.isEmpty()){
            clientService.updatePhone(client,phone);
        }
        if (cellPhone != null && !cellPhone.isEmpty()){
            clientService.updateCellPhone(client,cellPhone);
        }
        if (email != null && !email.isEmpty()){
            clientService.updateEmail(client,email);
        }
        if (userName != null && !userName.isEmpty()){
            clientService.updateUserName(client,userName);
        }
        if (password != null && !password.isEmpty()){
            clientService.updatePassword(client,password);
        }
        clientService.saveChanges(client);

        return new ResponseEntity<>("Cliente modificado con éxito",HttpStatus.OK);
    }

}