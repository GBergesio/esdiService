package esdi.Services.controllers.userControllers;

import esdi.Services.dtos.AdminDTO;
import esdi.Services.models.users.Admin;
import esdi.Services.services.AdminService;
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
    @GetMapping("/admins")
    ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    //  ADMIN POR ID
    @GetMapping("/admin/{id}")
    ResponseEntity<?> getUserDTO(@PathVariable Long id){
        return new ResponseEntity<>(adminService.getAdminByID(id), HttpStatus.OK);
    }

    //  ADMIN POR DNI
    @GetMapping("/admin/dni/{dni}")
    public ResponseEntity<?> getUserByDni(@PathVariable String dni) {

        Admin admin = adminService.getAdminByDNI(dni);

        if (admin == null){
            return new ResponseEntity<>("No se encontró administrador con el DNI ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(adminService.getAdminByDNI(dni), HttpStatus.OK);
    }

    //  CREAR ADMIN
    @Transactional
    @PostMapping("/admin")
    public ResponseEntity<Object> newAdmin(@RequestBody AdminDTO adminDTO){
        if(adminService.getAdminByUserName(adminDTO.getUser()) != null){
            return new ResponseEntity<>("Nombre de usuario ya existente",HttpStatus.BAD_REQUEST);
        }
        if(adminService.getAdminByDNI(adminDTO.getDni()) != null){
            return new ResponseEntity<>("DNI ya existente",HttpStatus.BAD_REQUEST);
        }
        if (adminDTO.getFirstName().isEmpty()){
            return new ResponseEntity<>("Nombre requerido", HttpStatus.BAD_REQUEST);
        }
        if (adminDTO.getLastName().isEmpty()){
            return new ResponseEntity<>("Apellido requerido",HttpStatus.BAD_REQUEST);
        }
        if (adminDTO.getDni().isEmpty()){
            return new ResponseEntity<>("DNI requerido",HttpStatus.BAD_REQUEST);
        }
        if (adminDTO.getUser().isEmpty()){
            return new ResponseEntity<>("Nombre de usuario requerido",HttpStatus.BAD_REQUEST);
        }
        if (adminDTO.getPassword().isEmpty()){
            return new ResponseEntity<>("Password requerido",HttpStatus.BAD_REQUEST);
        }
        adminService.saveAdmin(adminDTO);
        return new ResponseEntity<>("Administrador creado con éxito", HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/admin/modify")
    public ResponseEntity<?> editAdmin(
            @RequestParam String dni,
            @RequestParam(required = false) String firstName,@RequestParam(required = false) String lastName,@RequestParam(required = false) String email,
            @RequestParam(required = false) String userName, @RequestParam(required = false) String password) {

                if (dni.isEmpty()){
                    return new ResponseEntity<>("No se encontró DNI",HttpStatus.BAD_REQUEST);
                }

                Admin admin = adminService.getAdminByDNI(dni);

                if (admin == null){
                    return new ResponseEntity<>("No se encontró administrador",HttpStatus.BAD_REQUEST);
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

                if (adminService.getAllAdmins().stream().filter(adm -> adm.getUser().equals(userName)).count() > 0){
                    return new ResponseEntity<>("nombre de usuario existente", HttpStatus.BAD_REQUEST);
                }

                if (firstName != null && !firstName.isEmpty()){
                    adminService.updateFirstName(admin,firstName);
                }
                if (lastName != null && !lastName.isEmpty()){
                    adminService.updateLastName(admin,lastName);
                }
                if (email != null && !email.isEmpty()){
                    adminService.updateEmail(admin,email);
                }
                if (userName != null && !userName.isEmpty()){
                    adminService.updateUserName(admin,userName);
                }
                if (password != null && !password.isEmpty()){
                    adminService.updatePassword(admin,password);
                }
                adminService.saveChanges(admin);

                return new ResponseEntity<>("Admin modificado con éxito",HttpStatus.OK);
            }

    @Transactional
    @PatchMapping("/admin/delete")
        public ResponseEntity<?> delete(@RequestParam String dni){
        if (dni.isEmpty()){
            return new ResponseEntity<>("Ingrese DNI", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminService.getAdminByDNI(dni);

        if (admin == null){
            return new ResponseEntity<>("Admin no existe",HttpStatus.BAD_REQUEST);
        }
        adminService.delete(admin);
        return new ResponseEntity<>("Admin eliminado",HttpStatus.OK);

    }



}
