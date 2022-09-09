package esdi.Services.services.implement;
import esdi.Services.dtos.ProductDTO;
import esdi.Services.dtos.request.ProductDTORequest;
import esdi.Services.mappers.ProductMapper;
import esdi.Services.models.Currency;
import esdi.Services.models.Dolar;
import esdi.Services.models.products.Brand;
import esdi.Services.models.products.Category;
import esdi.Services.models.products.Iva;
import esdi.Services.models.products.Product;
import esdi.Services.repositories.*;
import esdi.Services.services.ProductService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    IvaRepository ivaRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    DolarRepository dolarRepository;

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
    @Override
    public ResponseEntity<?> createProduct(ProductDTORequest productDTORequest) {

        try{
            Iva iva = ivaRepository.findById(productDTORequest.getIvaId()).orElse(null);
            Category category = categoryRepository.findById(productDTORequest.getCategoryId()).orElse(null);
            Brand brand = brandRepository.findById(productDTORequest.getBrandId()).orElse(null);
            Dolar dolar = dolarRepository.findById(productDTORequest.getDolarId()).orElse(null);

            if (iva == null)
                return new ResponseEntity<>("Ingrese Iva",HttpStatus.BAD_REQUEST);

            if (category == null)
                return new ResponseEntity<>("Ingrese invalido",HttpStatus.BAD_REQUEST);

            if (brand == null)
                return new ResponseEntity<>("Ingrese marca",HttpStatus.BAD_REQUEST);

            if (productDTORequest.getCurrency().equals(Currency.DOLAR)){
                productDTORequest.setSalePrice(((productDTORequest.getCostPrice() * dolar.getPrice()) * iva.getIva()) * productDTORequest.getUtility());
            }

            if (productDTORequest.getCurrency().equals(Currency.PESO)){
                productDTORequest.setSalePrice((productDTORequest.getCostPrice() * iva.getIva()) * productDTORequest.getUtility());
            }

            Product product = new Product();
            product.setProductNumber(productDTORequest.getProductNumber());
            product.setDescription(productDTORequest.getDescription());
            product.setCostPrice(productDTORequest.getCostPrice());
            product.setSalePrice(productDTORequest.getSalePrice());
            product.setUtility(productDTORequest.getUtility());
            product.setCurrency(productDTORequest.getCurrency());

            product.setIva(iva);
            product.setCategory(category);
            product.setBrand(brand);

            if (dolar != null)
                product.setDolar(dolar);

            return new ResponseEntity<>(this.saveProductDTO(product),HttpStatus.CREATED);

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @Override
    public ProductDTO saveProductDTO(Product product) {
        return productMapper.toDTO(productRepository.save(product));
    }
}
