package esdi.Services.services.budget;

import esdi.Services.dtos.budget.OptionBudgetDTO;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.models.budgets.OptionBudget;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OptionBudgetService {

    ResponseEntity<?> allOptionBudgets();
    ResponseEntity<?> allOptionBudgetsByCompany(Authentication authentication);
    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> optionsByBudget(Authentication authentication, Long id);

    ResponseEntity<?> createOptionBudget(Authentication authentication, OptionRequest optionRequest, Long idBudget);

    ResponseEntity<?> updateOptionBudget(Authentication authentication,OptionRequest optionRequest, Long id);
    ResponseEntity<?> updateTotalOptionBudget(Authentication authentication,OptionRequest optionRequest, Long id);
    ResponseEntity<?> deleteOptionBudget(Authentication authentication, Long id);

}
