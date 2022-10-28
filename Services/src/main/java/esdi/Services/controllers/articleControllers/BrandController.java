package esdi.Services.controllers.articleControllers;
import esdi.Services.dtos.BrandDTO;
import esdi.Services.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping()
    ResponseEntity<?> getAllBrands(){
        return new ResponseEntity<>(brandService.findAllDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return brandService.findById(id);
    }

    @PostMapping()
    ResponseEntity<?> createCategory(@RequestBody BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }


    @GetMapping("/current/brandsByCompany")
    ResponseEntity<?> getBrandsByCompany(Authentication authentication) {
        return brandService.allBrandsByCompany(authentication);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> renameCategory(@PathVariable Long id, @RequestParam String name) {
        return brandService.renameBrand(id, name);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return brandService.deleteBrand(id);
    }

}
