package esdi.Services.controllers.articleControllers;
import esdi.Services.dtos.request.ProductRequest;
import esdi.Services.mappers.ProductMapper;
import esdi.Services.repositories.ProductRepository;
import esdi.Services.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/current")
    ResponseEntity<?> getCategoriesByCompany(Authentication authentication) {
        return productService.allProductsByCompany(authentication);
    }

    @PostMapping("/current")
    ResponseEntity<?> newProduct(@RequestBody ProductRequest productRequest, Authentication authentication){
        return productService.createProductByCompany(authentication, productRequest);
    }

    @PatchMapping("/current/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest, Authentication authentication) {
        return productService.updateProductByCompany(authentication, id, productRequest);
    }

    @DeleteMapping("/current/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, Authentication authentication) {
        return productService.deleteProductByCompany(authentication, id);
    }

}
