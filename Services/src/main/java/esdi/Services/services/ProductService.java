package esdi.Services.services;
import esdi.Services.dtos.ProductDTO;
import esdi.Services.dtos.request.ProductRequest;
import esdi.Services.models.products.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    ProductDTO getProductDTO(Product product);

    ProductDTO getProductByPN(String productNumber);

    List<ProductDTO> findAllDTO();

    ResponseEntity<?> findPN(String productNumber);

    ProductDTO saveProductDTO(Product product);

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> updateProductByCompany(Authentication authentication,Long id, ProductRequest productRequest);
    ResponseEntity<?> allProductsByCompany(Authentication authentication);

    ResponseEntity<?> createProductByCompany(Authentication authentication, ProductRequest productRequest);

    ResponseEntity<?> deleteProductByCompany(Authentication authentication,Long id);

}
