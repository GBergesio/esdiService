package esdi.Services.controllers;

import esdi.Services.services.NeighbourhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/nbh")
public class NeighbourhoodController {

    @Autowired
    NeighbourhoodService nbhS;

    @GetMapping()
    ResponseEntity<?> allNbh(Authentication authentication) {
        return nbhS.allNeighbourhoodByCompany(authentication);
    }
}
