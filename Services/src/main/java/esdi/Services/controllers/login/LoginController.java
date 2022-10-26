package esdi.Services.controllers.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Transactional
    @PostMapping()
    ResponseEntity<?> login(@RequestParam String user, @RequestParam String password){
        return new ResponseEntity<>("Login correcto", HttpStatus.OK);
    }
}
