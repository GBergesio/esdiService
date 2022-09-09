package esdi.Services.services;
import esdi.Services.dtos.ProductDTO;
import esdi.Services.dtos.request.ProductDTORequest;
import esdi.Services.models.products.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    ProductDTO getProductDTO(Product product);

    List<ProductDTO> findAllDTO();

    ResponseEntity<?> createProduct(ProductDTORequest productDTORequest);

    ProductDTO saveProductDTO(Product product);
}
