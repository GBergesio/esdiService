package esdi.Services.controllers.budget;

import esdi.Services.services.budget.OptionBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optionBudgets")
public class OptionBudgetController {

    @Autowired
    OptionBudgetService optionBudgetService;

    @GetMapping()
    ResponseEntity<?> getAllBudgets(){
        return new ResponseEntity<>(optionBudgetService.allOptionBudgets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id){
        return optionBudgetService.findById(id);
    }

//    @PostMapping()
//    ResponseEntity<?> newOption(@RequestBody OptionRequest option, Long idBudget, Long idPoS) {
//        return optionComponentService.createOptionComponent(option, idBudget,idPoS);
//    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOption(@PathVariable Long id){
        return optionBudgetService.deleteOptionBudget(id);
    }

}
