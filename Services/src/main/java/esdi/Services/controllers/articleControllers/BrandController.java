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

    @PostMapping("/current")
    ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO, Authentication authentication) {
        return brandService.createBrand(brandDTO, authentication);
    }


    @GetMapping("/current")
    ResponseEntity<?> getBrandsByCompany(Authentication authentication) {
        return brandService.allBrandsByCompany(authentication);
    }

    @PatchMapping("/current/{id}")
    ResponseEntity<?> renameBrand(@PathVariable Long id, @RequestParam String name, Authentication authentication) {
        return brandService.renameBrand(id, name, authentication);
    }

    @DeleteMapping("/current/{id}")
    ResponseEntity<?> deleteBrand(@PathVariable Long id, Authentication authentication) {
        return brandService.deleteBrand(id,authentication);
    }

}
