package esdi.Services.controllers;

import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class TechnicianController {

    @Autowired
    TechnicianService technicianService;


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

}
