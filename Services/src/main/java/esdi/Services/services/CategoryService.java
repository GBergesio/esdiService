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
        ResponseEntity<?> findById(Long id);

        ResponseEntity<?> allCategoriesByCompany(Authentication authentication);

        ResponseEntity<?> createCategory(CategoryDTO categoryDTO, Authentication authentication);

        ResponseEntity<?> renameCategory(Long id, String name, Authentication authentication);

        ResponseEntity<?> deleteCategory(Long id, Authentication authentication);
}
