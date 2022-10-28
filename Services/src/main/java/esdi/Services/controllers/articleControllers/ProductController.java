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

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @GetMapping()
    ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(productService.findAllDTO(), HttpStatus.OK);
    }

    @GetMapping("/pn/{productNumber}")
    ResponseEntity<?> getProductBy(@PathVariable String productNumber){
        return productService.findPN(productNumber);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<?> getProductByID(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/current/productsByCompany")
    ResponseEntity<?> getCategoriesByCompany(Authentication authentication) {
        return productService.allProductsByCompany(authentication);
    }

    @PostMapping()
    ResponseEntity<?> newProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

}
