package esdi.Services.dtos.budget;

import esdi.Services.enums.StatusBudget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class BudgetRequest {

    private StatusBudget statusBudget;
    private LocalDateTime issueDate;
    private LocalDateTime dueDate;

}
