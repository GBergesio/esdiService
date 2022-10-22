package esdi.Services.controllers.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.request.DeviceRequest;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.services.budget.OptionComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optionComponent")
public class OptionComponentController {

    @Autowired
    OptionComponentService optionComponentService;

    @GetMapping()
    ResponseEntity<?> getAllOptionComponents(){
        return new ResponseEntity<>(optionComponentService.allOptionsComponent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id){
        return optionComponentService.findById(id);
    }

    @PostMapping()
    ResponseEntity<?> newOption(@RequestBody OptionRequest option, Long idBudget, Long idPoS) {
        return optionComponentService.createOptionComponent(option, idBudget,idPoS);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOption(@PathVariable Long id){
        return optionComponentService.deleteOption(id);
    }


}