package esdi.Services.dtos.request;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.models.budgets.OptionComponent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class OptionRequest {

    private double totalOptionBudget;
    private String comment;
    private Boolean selected;

    List<OptionComponentDTO> optionComponents;

}
