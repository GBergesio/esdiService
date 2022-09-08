package esdi.Services.services.implement;

import esdi.Services.dtos.ProductDTO;
import esdi.Services.mappers.ProductMapper;
import esdi.Services.models.Product;
import esdi.Services.repositories.ProductRepository;
import esdi.Services.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductDTO getProductDTO(Product product) {
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> findAllDTO() {
        List<Product> allprod = productRepository.findAll();
        return productMapper.toDTO(allprod);
    }


}
