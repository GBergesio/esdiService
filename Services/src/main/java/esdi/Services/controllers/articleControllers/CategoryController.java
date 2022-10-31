package esdi.Services.controllers.articleControllers;

import esdi.Services.dtos.CategoryDTO;
import esdi.Services.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/current")
    ResponseEntity<?> getCategoriesByCompany(Authentication authentication) {
        return categoryService.allCategoriesByCompany(authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO, Authentication authentication) {
        return categoryService.createCategory(categoryDTO, authentication);
    }

    @PatchMapping("/current/{id}")
    ResponseEntity<?> renameCategory(@PathVariable Long id, @RequestParam String name, Authentication authentication) {
        return categoryService.renameCategory(id, name, authentication);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id, Authentication authentication) {
        return categoryService.deleteCategory(id,authentication);
    }

}
