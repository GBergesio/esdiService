package esdi.Services.controllers;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.services.AdminService;
import esdi.Services.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    AdminService adminService;


    //  TODOS LOS ADMIN
    @GetMapping("/admins/")
    ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    //  TODOS LOS ADMIN DTO
//    @GetMapping("/adminsDTO/")
//    ResponseEntity<?> getUsers(){
//        return new ResponseEntity<>(adminService.getAdminDTO(), HttpStatus.OK);
//    }

    //  ADMIN POR ID
//    @GetMapping("/admins/{id}")
//    ResponseEntity<?> getUserDTO(@PathVariable Long id){
//        return new ResponseEntity<>(adminService.getAdminDTO(id), HttpStatus.OK);
//    }

    //  ADMIN POR DNI
    @GetMapping("/admins/dni/{dni}")
    public ResponseEntity<?> getUserByDni(@PathVariable String dni) {
        return new ResponseEntity<>(adminService.getAdminByDNI(dni), HttpStatus.OK);
    }

    //  CREAR ADMIN
    @Transactional
    @PostMapping("/admin")
    public ResponseEntity<Object> newAdmin(@RequestBody AdminDTO adminDTO){
        if(adminService.getAdminByUserName(adminDTO.getUser()) != null){
            return new ResponseEntity<>("Nombre de usuario ya existente",HttpStatus.FORBIDDEN);
        }
        if(adminService.getAdminByDNI(adminDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.FORBIDDEN);
        }
        if (adminDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido", HttpStatus.FORBIDDEN);
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
        adminService.saveAdmin(adminDTO);
        return new ResponseEntity<>("Administrador creado con éxito", HttpStatus.CREATED);
    }


}
