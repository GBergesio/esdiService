package esdi.Services.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OptionComponentDTO {
    private long id;
    private long idPoS;
    private String name;
    private double pricePoS;
    private double quantity;
    private double totalPrice;

}
