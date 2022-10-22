package esdi.Services.services.budget;

import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.dtos.budget.BudgetRequest;
import esdi.Services.models.budgets.Budget;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BudgetService {

            Budget saveBudget(Budget budget);

            BudgetDTO saveBudgetDTO(Budget budget);

            List<BudgetDTO> findAllDTO();

            ResponseEntity<?> allBudgets();

            ResponseEntity<?> findById(Long id);

            ResponseEntity<?> createBudget(BudgetRequest request, Long idClient, Long idOrder);

            ResponseEntity<?> updateBudget(Long id);

            ResponseEntity<?> deleteBudget(Long id);
}
