package esdi.Services.controllers.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.request.DeviceRequest;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.services.budget.OptionComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optionComponent")
public class OptionComponentController {

    @Autowired
    OptionComponentService optionComponentService;

    @GetMapping("/current")
    ResponseEntity<?> getOptionsByCompany(Authentication authentication){
        return new ResponseEntity<>(optionComponentService.allOptionsComponentByCompany(authentication), HttpStatus.OK);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteOption(Authentication authentication, @PathVariable Long id){
        return optionComponentService.deleteOption(authentication, id);
    }


}