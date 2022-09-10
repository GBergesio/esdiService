package esdi.Services.controllers.userControllers;

import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.models.users.Technician;
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

    ////  TECNICOS POR DNI
        @GetMapping("/technician/dni/{dni}")
        public ResponseEntity<?> getUserByDni(@PathVariable String dni) {

            Technician technician = technicianService.getTechDNI(dni);

            if (technician == null){
                return new ResponseEntity<>("No se encontró tecnico con el DNI Ingresado",HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(technicianService.getTechDNI(dni), HttpStatus.OK);
        }

    @Transactional
    @PostMapping("/technician")
    public ResponseEntity<Object> newTechnician(@RequestBody TechnicianDTO technicianDTO){
        if(technicianService.getTechUserName(technicianDTO.getUser()) != null){
            return new ResponseEntity<>("Nombre de usuario ya existente",HttpStatus.BAD_REQUEST);
        }
        if(technicianService.getTechDNI(technicianDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.BAD_REQUEST);
        }
        if (technicianDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido",HttpStatus.BAD_REQUEST);
        }
        if (technicianDTO.getLastName().isEmpty()){
            return new ResponseEntity<>("Apellido requerido",HttpStatus.BAD_REQUEST);
        }
//        if (technicianDTO.getDni().isEmpty()){
//            return new ResponseEntity<>("DNI requerido",HttpStatus.FORBIDDEN);
//        }
        if (technicianDTO.getUser().isEmpty()){
            return new ResponseEntity<>("Nombre de usuario requerido",HttpStatus.BAD_REQUEST);
        }
        if (technicianDTO.getPassword().isEmpty()){
            return new ResponseEntity<>("Password requerido",HttpStatus.BAD_REQUEST);
        }

        technicianService.saveTechnician(technicianDTO);
        return new ResponseEntity<>("Tecnico creado con éxito", HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/technician/modify")
        public ResponseEntity<?> editTechnician(
                @RequestParam String dni,
                @RequestParam(required = false) String firstName,@RequestParam(required = false) String lastName,@RequestParam(required = false) String email,
                @RequestParam(required = false) String userName, @RequestParam(required = false) String password) {

            if (dni.isEmpty()){
                return new ResponseEntity<>("No se encontró DNI",HttpStatus.BAD_REQUEST);
            }

            Technician technician = technicianService.getTechDNI(dni);

            if (technician == null){
                return new ResponseEntity<>("No se encontró tecnico",HttpStatus.BAD_REQUEST);
            }

            if (firstName == null && lastName == null && email == null && userName == null && password == null){
                return new ResponseEntity<>("No se ha proporcionado ningun cambio", HttpStatus.BAD_REQUEST);
            }
            if (firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && userName.isEmpty() && password.isEmpty()){
            return new ResponseEntity<>("No se ha proporcionado ningun cambio", HttpStatus.BAD_REQUEST);
            }
            if (firstName == null){
                return new ResponseEntity<>("No se puede enviar un nombre vacio ", HttpStatus.BAD_REQUEST);
            }
            if (email == null){
                return new ResponseEntity<>("No se puede enviar un email vacio", HttpStatus.BAD_REQUEST);
            }
            if (userName == null){
                return new ResponseEntity<>("No se puede enviar un nombre de usuario vacio ", HttpStatus.BAD_REQUEST);
            }
            if (password == null){
                return new ResponseEntity<>("No se puede enviar una contraseña vacia", HttpStatus.BAD_REQUEST);
            }

            if (technicianService.getAllTech().stream().filter(tech -> tech.getUser().equals(userName)).count() > 0){
                return new ResponseEntity<>("nombre de usuario existente", HttpStatus.BAD_REQUEST);
            }

            if (firstName != null && !firstName.isEmpty()){
                technicianService.updateFirstName(technician,firstName);
            }
            if (lastName != null && !lastName.isEmpty()){
                technicianService.updateLastName(technician,lastName);
            }
            if (email != null && !email.isEmpty()){
                technicianService.updateEmail(technician,email);
            }
            if (userName != null && !userName.isEmpty()){
                technicianService.updateUserName(technician,userName);
            }
            if (password != null && !password.isEmpty()){
                technicianService.updatePassword(technician,password);
            }
            technicianService.saveChanges(technician);

            return new ResponseEntity<>("Tecnico modificado con éxito",HttpStatus.OK);
        }


}
