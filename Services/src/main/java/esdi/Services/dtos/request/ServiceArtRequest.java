package esdi.Services.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceArtRequest {

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    private Long ivaId;

    private Long categoryId;

}
