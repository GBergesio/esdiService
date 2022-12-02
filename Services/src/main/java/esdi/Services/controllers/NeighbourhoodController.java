package esdi.Services.controllers;

import esdi.Services.dtos.NeighborhoodDTO;
import esdi.Services.dtos.request.NbhRequest;
import esdi.Services.services.NeighbourhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/nbh")
public class NeighbourhoodController {

    @Autowired
    NeighbourhoodService nbhS;

    @GetMapping("/current")
    ResponseEntity<?> allNbh(Authentication authentication) {
        return nbhS.allNeighbourhoodByCompany(authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> createNbh(Authentication authentication, @RequestBody NbhRequest nbhRequest) {
        return nbhS.newNbh(authentication,nbhRequest);
    }
}
