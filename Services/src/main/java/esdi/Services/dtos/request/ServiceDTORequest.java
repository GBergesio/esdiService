package esdi.Services.dtos.request;

import esdi.Services.dtos.CategoryDTO;
import esdi.Services.dtos.IvaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceDTORequest {

    private long id;

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    private Long ivaId;

    private Long categoryId;



}
