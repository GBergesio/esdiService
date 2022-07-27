package esdi.Services.controllers;

import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class TechnicianController {

    @Autowired
    TechnicianService technicianService;

    //  TODOS LOS TECNICOS
        @GetMapping("/technicians/")
        ResponseEntity<?> getAllTech(){
            return new ResponseEntity<>(technicianService.getAllTech(), HttpStatus.OK);
        }

    ////  TODOS LOS TECNICOS DTO
    //    @GetMapping("/clientsDTO/")
    //    ResponseEntity<?> getUsers(){
    //        return new ResponseEntity<>(clientService.getClientsDTO(), HttpStatus.OK);
    //    }
    //
    ////  TECNICOS POR ID
    //    //agregar otro responseEntity para cuando no haya usuario con ese id
    //    @GetMapping("/clients/{id}")
    //    ResponseEntity<?> getUserDTO(@PathVariable Long id){
    //        return new ResponseEntity<>(clientService.getUserDTO(id), HttpStatus.OK);
    //    }
    //
    ////  TECNICOS POR DNI
    ////agregar otro responseEntity para cuando no haya usuario con ese dni
    //    @GetMapping("/clients/dni/{dni}")
    //    public ResponseEntity<?> getUserByDni(@PathVariable String dni) {
    //        return new ResponseEntity<>(clientService.getUserByDNI(dni), HttpStatus.OK);
    //    }


    @Transactional
    @PostMapping("/technician")
    public ResponseEntity<Object> newTechnician(@RequestBody TechnicianDTO technicianDTO){
        if(technicianService.getTechUserName(technicianDTO.getUser()) != null){
            return new ResponseEntity<>("Nombre de usuario ya existente",HttpStatus.FORBIDDEN);
        }
        if(technicianService.getTechDNI(technicianDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getLastName().isEmpty()){
            return new ResponseEntity<>("Apellido requerido",HttpStatus.FORBIDDEN);
        }
//        if (technicianDTO.getDni().isEmpty()){
//            return new ResponseEntity<>("DNI requerido",HttpStatus.FORBIDDEN);
//        }
        if (technicianDTO.getUser().isEmpty()){
            return new ResponseEntity<>("Nombre de usuario requerido",HttpStatus.FORBIDDEN);
        }
        if (technicianDTO.getPassword().isEmpty()){
            return new ResponseEntity<>("Password requerido",HttpStatus.FORBIDDEN);
        }

        technicianService.saveTechnician(technicianDTO);
        return new ResponseEntity<>("Tecnico creado con éxito", HttpStatus.CREATED);
    }

    //@Transactional
    //    @PatchMapping("/technician/modify")
    //    public ResponseEntity<?> editTechnician(
    //            @RequestParam String dni,
    //            @RequestParam(required = false) String firstName
    ////            , @RequestParam(required = false) String neighborhood
    //            ,@RequestParam(required = false) String lastName,
    //            @RequestParam(required = false) String email, @RequestParam(required = false) String userName, @RequestParam(required = false) String password
    //            ){
    //
    //        if (dni.isEmpty()){
    //            return new ResponseEntity<>("No se encontró DNI",HttpStatus.FORBIDDEN);
    //        }
    //
    //        Technician technician = technicianService.getTechByDNI(dni);
    //
    //        if (technician == null){
    //            return new ResponseEntity<>("No se encontró tecnico",HttpStatus.FORBIDDEN);
    //        }
    //
    //        if (firstName == null && lastName == null && address == null && phone == null && cellPhone == null && email == null && userName == null && password == null){
    //            return new ResponseEntity<>("No se ha proporcionado ningun cambio", HttpStatus.FORBIDDEN);
    //        }
    //        if (firstName == null){
    //            return new ResponseEntity<>("No se puede enviar un nombre vacio ", HttpStatus.BAD_REQUEST);
    //        }
    //        if (email == null){
    //            return new ResponseEntity<>("No se puede enviar un email vacio", HttpStatus.BAD_REQUEST);
    //        }
    //        if (userName == null){
    //            return new ResponseEntity<>("No se puede enviar un nombre de usuario vacio ", HttpStatus.BAD_REQUEST);
    //        }
    //        if (password == null){
    //            return new ResponseEntity<>("No se puede enviar una contraseña vacia", HttpStatus.BAD_REQUEST);
    //        }
    //
    //        if (clientService.getAllUsers().stream().filter(client1 -> client1.getUser().equals(userName)).count() > 0){
    //            return new ResponseEntity<>("nombre de usuario existente", HttpStatus.BAD_REQUEST);
    //        }
    //
    //        if (firstName != null && !firstName.isEmpty()){
    //            technicianService.updateFirstName(technician,firstName);
    //        }
    //        if (lastName != null && !lastName.isEmpty()){
    //            technicianService.updateLastName(technician,lastName);
    //        }
    //        if (email != null && !email.isEmpty()){
    //            technicianService.updateEmail(technician,email);
    //        }
    //        if (userName != null && !userName.isEmpty()){
    //            technicianService.updateUserName(technician,userName);
    //        }
    //        if (password != null && !password.isEmpty()){
    //            technicianService.updatePassword(technician,password);
    //        }
    //        technicianService.saveChanges(technician);
    //
    //        return new ResponseEntity<>("Tecnico modificado con éxito",HttpStatus.OK);
    //    }


}
