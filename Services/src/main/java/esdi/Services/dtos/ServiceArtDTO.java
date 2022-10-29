package esdi.Services.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceArtDTO {

    private Long id;

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    private IvaDTO iva;
    private CategoryDTO category;
    private Boolean deleted;

}
