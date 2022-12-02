package esdi.Services.controllers.login;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@CrossOrigin()
@RestController
@RequestMapping("/currentUser")
public class CurrentAuth {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Transactional
    @GetMapping()
    ResponseEntity<?> getCurrentCompany(Authentication authentication) {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

//        return ResponseEntity.ok(userDetails);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

}
