package esdi.Services.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceDTO {

    private long id;

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    private IvaDTO iva;

    private CategoryDTO category;

}
