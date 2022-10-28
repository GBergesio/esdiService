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

    @GetMapping()
    ResponseEntity<?> getAllBudgets(){
        return new ResponseEntity<>(budgetService.allBudgets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id){
        return budgetService.findById(id);
    }

    @GetMapping("/current/budgetsByCompany")
    ResponseEntity<?> getBudgetsByCompany(Authentication authentication) {
        return budgetService.allBudgetsByCompany(authentication);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBudget(@PathVariable Long id){
        return budgetService.deleteBudget(id);
    }

    @PostMapping()
    ResponseEntity<?> newBudget(@RequestBody BudgetRequest request, Long idClient, Long idOrder) {
        return budgetService.createBudget(request, idClient,idOrder);
    }
}
