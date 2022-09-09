package esdi.Services.controllers;
import esdi.Services.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

        @Autowired
        CategoryService categoryService;

        @GetMapping()
        ResponseEntity<?> getAllCategories(){
            return new ResponseEntity<>(categoryService.findAllDTO(), HttpStatus.OK);
        }

}
