package esdi.Services.dtos.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OptionBudgetDTO {

    private long id;
    private double total;
    private String comment;
    private Boolean selected;
    private Boolean deleted;

    List<OptionComponentDTO> optionComponents;

}
