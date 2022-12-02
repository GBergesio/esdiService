package esdi.Services.controllers.budget;

import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.services.budget.OptionBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optionBudgets")
public class OptionBudgetController {

    @Autowired
    OptionBudgetService optionBudgetService;

    @GetMapping("/current")
    ResponseEntity<?> getOptionsByCompany(Authentication authentication){
        return new ResponseEntity<>(optionBudgetService.allOptionBudgetsByCompany(authentication), HttpStatus.OK);
    }

    @GetMapping("/current/budget/{id}")
    ResponseEntity<?> getOptionByBudget(Authentication authentication,@PathVariable Long id){
        return new ResponseEntity<>(optionBudgetService.optionsByBudget(authentication,id), HttpStatus.OK);
    }

    @PostMapping("/current")
    ResponseEntity<?> newOption(Authentication authentication,@RequestBody OptionRequest option, Long idBudget) {
        return optionBudgetService.createOptionBudget(authentication,option, idBudget);
    }

    @PatchMapping("/current/{id}")
    ResponseEntity<?> updateOption(Authentication authentication,@RequestBody OptionRequest option, Long optionBudget) {
        return optionBudgetService.updateOptionBudget(authentication,option, optionBudget);
    }

    @PatchMapping("/current/total/{id}")
    ResponseEntity<?> updateTotalOption(Authentication authentication,@RequestBody OptionRequest option, Long optionBudget) {
        return optionBudgetService.updateTotalOptionBudget(authentication,option, optionBudget);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteOption(@PathVariable Long id, Authentication authentication){
        return optionBudgetService.deleteOptionBudget(authentication, id);
    }

}
