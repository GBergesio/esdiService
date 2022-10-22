package esdi.Services.dtos.budget;

import esdi.Services.enums.StatusBudget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class BudgetDTO {

    private int budgetNumber;
    private int orderNumber;
    private String client;
    private StatusBudget statusBudget;
    private LocalDateTime issueDate;
    private LocalDateTime confirmationDate;
    private LocalDateTime dueDate;
    private double subtotal;
    private double total;
    private List<OptionBudgetDTO> options;

}
