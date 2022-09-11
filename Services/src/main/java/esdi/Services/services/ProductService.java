package esdi.Services.services;
import esdi.Services.dtos.ProductDTO;
import esdi.Services.dtos.request.ProductDTORequest;
import esdi.Services.models.products.Category;
import esdi.Services.models.products.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    ProductDTO getProductDTO(Product product);

    ProductDTO getProductByPN(String productNumber);

    List<ProductDTO> findAllDTO();

    ResponseEntity<?> findPN(String productNumber);

    ResponseEntity<?> createProduct(ProductDTORequest productDTORequest);

    ProductDTO saveProductDTO(Product product);

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> editProduct(Long id, ProductDTORequest productDTORequest);

}
