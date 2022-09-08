package esdi.Services.services;

import esdi.Services.dtos.ProductDTO;
import esdi.Services.models.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    ProductDTO getProductDTO(Product product);

    List<ProductDTO> findAllDTO();
}
