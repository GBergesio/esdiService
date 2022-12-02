package esdi.Services.services.budget;

import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.dtos.budget.BudgetRequest;
import esdi.Services.models.budgets.Budget;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BudgetService {

            Budget saveBudget(Budget budget);

            BudgetDTO saveBudgetDTO(Budget budget);

            List<BudgetDTO> findAllDTO();

            ResponseEntity<?> allBudgets();

            ResponseEntity<?> findById(Long id);
            ResponseEntity<?> allBudgetsByCompany(Authentication authentication);

            ResponseEntity<?> createBudget(BudgetRequest request, Long idOrder, Authentication authentication);

            ResponseEntity<?> updateBudget(BudgetRequest request,Long idBudget, Authentication authentication);

            ResponseEntity<?> deleteBudget(Long id, Authentication authentication);
}
