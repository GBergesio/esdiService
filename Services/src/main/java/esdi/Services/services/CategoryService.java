package esdi.Services.services;
import esdi.Services.dtos.CategoryDTO;
import esdi.Services.models.products.Category;
import java.util.List;

public interface CategoryService {

        Category saveCategory(Category category);

        CategoryDTO getCategoryDTO(Category category);

        List<CategoryDTO> findAllDTO();
}
