package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.BrandDTO;
import esdi.Services.models.products.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BrandMapper implements GenericaMapper<BrandDTO, Brand> {
}
