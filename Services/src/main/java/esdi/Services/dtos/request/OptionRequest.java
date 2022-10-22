package esdi.Services.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OptionRequest {

    private double quantity;
    private double totalPrice;
    private String name;

}
