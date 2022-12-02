package esdi.Services.controllers.articleControllers;

import esdi.Services.dtos.DolarDTO;
import esdi.Services.services.DolarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dolar")
public class DolarController {

    @Autowired
    DolarService dolarService;

    @GetMapping("/current")
    ResponseEntity<?> getDolarByCompany(Authentication authentication) {
        return dolarService.dollarByCompany(authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> newDolarByCompany(Authentication authentication, DolarDTO dolarDTO) {
        return dolarService.newDollarByCompany(authentication, dolarDTO);
    }


}
