package esdi.Services.controllers;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

//    @Autowired
//    UserServiceImpl userServiceImpl;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    //Lista de usuarios-clientes
    @GetMapping("/users/")
    ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(userService.getListUsersDTO(), HttpStatus.OK);
    }

    //Usuario especifico por id
    @GetMapping("/users/{id}")
    ResponseEntity<?> getUserDTO(@PathVariable Long id){return new ResponseEntity<>(userService.getUserDTO(id), HttpStatus.OK);
    }

    //Usuario especifico por dni
    @GetMapping("/users/dni/{dni}")
    public ResponseEntity<?> getUserByDni(@PathVariable String dni) {
        return new ResponseEntity<>(userService.getUserByDNI(dni), HttpStatus.OK);
    }

    //Personal de trabajo por usuario
//    @GetMapping("/users/personal/{user}")
//    public ResponseEntity<?> getUserByUserName(@PathVariable String user) {
//        return new ResponseEntity<>(userService.getUserByUserName(user), HttpStatus.OK);
//    }


    //Usuario logueado
    //    @GetMapping("/users/current")
    //    public ResponseEntity<?> getCurrentUser(Authentication authentication){
    //        return(new ResponseEntity<>(new UserDTO(userService.getCurrentUser(authentication)),HttpStatus.ACCEPTED));
    //    }
    @Transactional
    @PostMapping("/users/")
    public ResponseEntity<Object> newClient(@RequestBody ClientDTO clientDTO){

//        if(userDTO.getEmail().isEmpty()){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }

        //que devuelva como mensaje DNI ya existente y el nombre del cliente existente
        if(userService.getUserByDNI(clientDTO.getDni()) != null){
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

        userService.saveUser(clientDTO);
        return new ResponseEntity<>("Cliente registrado con éxito", HttpStatus.CREATED);

    }

    @Transactional
    @PostMapping("/users/admin")
    public ResponseEntity<Object> newAdmin(@RequestBody AdminDTO adminDTO){
        if(userService.getUserByUserName(adminDTO.getUser()) != null){
            return new ResponseEntity<>("Nombre de usuario ya existente",HttpStatus.FORBIDDEN);
        }
        if(userService.getUserByDNI(adminDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.FORBIDDEN);
        }
        if (adminDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido",HttpStatus.FORBIDDEN);
        }
        if (adminDTO.getLastName().isEmpty()){
            return new ResponseEntity<>("Apellido requerido",HttpStatus.FORBIDDEN);
        }
        if (adminDTO.getDni().isEmpty()){
            return new ResponseEntity<>("DNI requerido",HttpStatus.FORBIDDEN);
        }
        if (adminDTO.getUser().isEmpty()){
            return new ResponseEntity<>("Nombre de usuario requerido",HttpStatus.FORBIDDEN);
        }
        if (adminDTO.getPassword().isEmpty()){
            return new ResponseEntity<>("Password requerido",HttpStatus.FORBIDDEN);
        }
        userService.saveAdmin(adminDTO);
        return new ResponseEntity<>("Administrador creado con éxito", HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/users/technician")
    public ResponseEntity<Object> newTechnician(@RequestBody TechnicianDTO technicianDTO){
        if(userService.getUserByUserName(technicianDTO.getUser()) != null){
            return new ResponseEntity<>("Nombre de usuario ya existente",HttpStatus.FORBIDDEN);
        }
        if(userService.getUserByDNI(technicianDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getLastName().isEmpty()){
            return new ResponseEntity<>("Apellido requerido",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getDni().isEmpty()){
            return new ResponseEntity<>("DNI requerido",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getUser().isEmpty()){
            return new ResponseEntity<>("Nombre de usuario requerido",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getPassword().isEmpty()){
            return new ResponseEntity<>("Password requerido",HttpStatus.FORBIDDEN);
        }

        userService.saveTechnician(technicianDTO);
        return new ResponseEntity<>("Tecnico creado con éxito", HttpStatus.CREATED);
    }

//    @Transactional
//    @PatchMapping("/users/")
//    public ResponseEntity<Object> editClient


}
