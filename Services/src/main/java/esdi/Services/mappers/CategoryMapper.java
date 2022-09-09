package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.CategoryDTO;
import esdi.Services.models.products.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper implements GenericaMapper<CategoryDTO, Category> {

}
