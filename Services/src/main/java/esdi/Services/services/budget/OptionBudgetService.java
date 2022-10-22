package esdi.Services.services.budget;

import esdi.Services.dtos.budget.OptionBudgetDTO;
import esdi.Services.models.budgets.OptionBudget;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OptionBudgetService {

    OptionBudget saveOptionBudget(OptionBudget optionBudget);

    OptionBudgetDTO saveOptionBudgetDTO(OptionBudget optionBudget);

    List<OptionBudgetDTO> findAllDTO();

    ResponseEntity<?> allOptionBudgets();

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> createOptionBudget(OptionBudgetDTO optionBudgetDTO);

    ResponseEntity<?> updateOptionBudget(Long id);

    ResponseEntity<?> deleteOptionBudget(Long id);

}
