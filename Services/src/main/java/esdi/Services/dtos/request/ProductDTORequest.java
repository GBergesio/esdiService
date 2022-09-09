package esdi.Services.dtos.request;

import esdi.Services.models.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductDTORequest {
    private String productNumber;

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    private Currency currency;

    private Long ivaId;

    private Long categoryId;

    private Long dolarId;

    private Long brandId;

}
