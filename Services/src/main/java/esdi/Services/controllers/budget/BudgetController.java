package esdi.Services.controllers.budget;

import esdi.Services.dtos.budget.BudgetRequest;
import esdi.Services.services.budget.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @GetMapping("/current")
    ResponseEntity<?> getBudgetsByCompany(Authentication authentication) {
        return budgetService.allBudgetsByCompany(authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> newBudget(@RequestBody BudgetRequest request, Long idOrder, Authentication authentication) {
        return budgetService.createBudget(request, idOrder, authentication);
    }

    @PatchMapping("/current/{id}")
    ResponseEntity<?> updateBudget(@RequestBody BudgetRequest request, Long idBudget, Authentication authentication) {
        return budgetService.updateBudget(request, idBudget, authentication);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteBudget(@PathVariable Long id, Authentication authentication){
        return budgetService.deleteBudget(id, authentication);
    }


}
