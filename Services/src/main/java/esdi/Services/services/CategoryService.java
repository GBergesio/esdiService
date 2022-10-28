package esdi.Services.services;
import esdi.Services.dtos.CategoryDTO;
import esdi.Services.models.products.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

        Category saveCategory(Category category);

        CategoryDTO getCategoryDTO(Category category);

        List<CategoryDTO> findAllDTO();

        ResponseEntity<?> allCategories();

        ResponseEntity<?> allCategoriesByCompany(Authentication authentication);


        ResponseEntity<?> findById(Long id);

        ResponseEntity<?> createCategory(CategoryDTO categoryDTO);

        ResponseEntity<?> renameCategory(Long id, String name);

        ResponseEntity<?> deleteCategory(Long id);
}
