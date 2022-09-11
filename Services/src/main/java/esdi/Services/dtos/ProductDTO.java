package esdi.Services.dtos;
import esdi.Services.models.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductDTO {

    private String productNumber;

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    private Currency currency;

    private IvaDTO iva;

    private CategoryDTO category;

    private Double dolar;

    private BrandDTO brand;
}
