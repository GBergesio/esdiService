package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.ProductDTO;
import esdi.Services.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductMapper implements GenericaMapper<ProductDTO, Product> {

}
