package esdi.Services.controllers.articleControllers;
import esdi.Services.dtos.CategoryDTO;
import esdi.Services.services.CategoryService;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    ResponseEntity<?> getAllCategories(){
    return categoryService.allCategories();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping()
    ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> renameCategory(@PathVariable Long id, @RequestParam String name){
        return categoryService.renameCategory(id,name);
    }




}