package esdi.Services.services.implement;
import esdi.Services.dtos.ProductDTO;
import esdi.Services.dtos.request.ProductRequest;
import esdi.Services.mappers.ProductMapper;
import esdi.Services.models.Currency;
import esdi.Services.models.Dolar;
import esdi.Services.models.products.*;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.*;
import esdi.Services.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductDTO getProductDTO(Product product) {
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO getProductByPN(String productNumber) {
        return productMapper.toDTO(productRepository.findByProductNumber(productNumber));
    }

    @Override
    public List<ProductDTO> findAllDTO() {
        List<Product> allprod = productRepository.findAll();
        return productMapper.toDTO(allprod);
    }

    @Override
    public ResponseEntity<?> allProductsByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Product> productList = productRepository.findAllByCompany(company);
        List<Product> productsAvailable = productList.stream().filter(product -> product.getDeleted().equals(false)).collect(Collectors.toList());

        return new ResponseEntity<>(productMapper.toDTO(productsAvailable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteProductByCompany(Authentication authentication, Long id) {
        Product product = productRepository.findById(id).orElse(null);
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Product> productList = company.getProducts();

        if (productList.indexOf(product) == -1)
            return new ResponseEntity<>("No se encuentra producto con el id seleccionado", HttpStatus.BAD_REQUEST);
        else{
            product.setDeleted(true);
        }
        productRepository.save(product);

        return new ResponseEntity<>("Producto eliminado exitosamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findPN(String productNumber) {
        try{
            ProductDTO product =  getProductByPN(productNumber);

            if (product == null){
                return new ResponseEntity<>("No se encontró producto con el codigo ingresado",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(getProductByPN(productNumber), HttpStatus.OK);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createProductByCompany(Authentication authentication, ProductRequest productRequest) {
        Company company = companyRepository.findByUsername(authentication.getName());

        try{
            Iva iva = ivaRepository.findById(productRequest.getIvaId()).orElse(null);
            Category category = categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
            Brand brand = brandRepository.findById(productRequest.getBrandId()).orElse(null);
            Dolar dolar = dolarRepository.findByCompany(company);
            Product product = new Product();
            List<Product> productList = productRepository.findAllByCompany(company);

            if (iva == null)
                return new ResponseEntity<>("Ingrese Iva",HttpStatus.BAD_REQUEST);

            if (category == null)
                return new ResponseEntity<>("Ingrese categoria",HttpStatus.BAD_REQUEST);

            if (brand == null)
                return new ResponseEntity<>("Ingrese marca",HttpStatus.BAD_REQUEST);

            if (productRequest.getProductNumber().isBlank() || productRequest.getProductNumber().isEmpty()){
                return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
            }

            if (productRequest.getProductNumber() != null) {
                for (Product productE : productList) {
                    if (productE.getProductNumber().equals(productRequest.getProductNumber())) {
                        return new ResponseEntity<>("PN en uso", HttpStatus.BAD_REQUEST);
                    }
                    else{
                        product.setProductNumber(productRequest.getProductNumber().toUpperCase());
                    }
                }
            }

            if (productRequest.getCurrency().equals(null)){
                return new ResponseEntity<>("Ingrese un tipo de moneda",HttpStatus.BAD_REQUEST);
            }
            if(productRequest.getCurrency().equals(Currency.DOLAR)){
                product.setCurrency(Currency.DOLAR);
                product.setDolar(dolar.getPrice());
            }
            if(productRequest.getCurrency().equals(Currency.PESO)){
                product.setCurrency(Currency.PESO);
                product.setDolar(null);
            }

            if(productRequest.getCostPrice() == 0){
                return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
            }
            if(productRequest.getCostPrice() > 0){
                product.setCostPrice(productRequest.getCostPrice());
            }
            if(productRequest.getSalePrice() == 0){
                return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
            }
            if(productRequest.getSalePrice() > 0){
                product.setSalePrice(productRequest.getSalePrice());
            }
            if(productRequest.getUtility() == 0){
                return new ResponseEntity<>("Ingrese una utilidad mayor a 0",HttpStatus.BAD_REQUEST);
            }
            if(productRequest.getUtility() > 0){
                product.setUtility(productRequest.getUtility());
            }

            if (productRequest.getDescription().isEmpty() || productRequest.getDescription().isBlank() || productRequest.getDescription() == null){
                return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
            } else {
                product.setDescription(productRequest.getDescription());
            }

            product.setIva(iva);
            product.setCategory(category);
            product.setBrand(brand);
            product.setCompany(company);
            product.setDeleted(false);

            return new ResponseEntity<>(this.saveProductDTO(product),HttpStatus.CREATED);

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @Override
    public ProductDTO saveProductDTO(Product product) {
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        ProductDTO productDTO = productMapper.toDTO(productRepository.findById(id).orElse(null));

        if (productDTO == null)
            return new ResponseEntity<>("No se encontro producto con el id ingresado",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateProductByCompany(Authentication authentication, Long id, ProductRequest productRequest) {

        Company company = companyRepository.findByUsername(authentication.getName());
        Product product = productRepository.findById(id).orElse(null);
        List<Product> productList = productRepository.findAllByCompany(company);
        Dolar dolarCompany = dolarRepository.findByCompany(company);
        Double valueDolarCompany = dolarCompany.getPrice();
        Iva iva = ivaRepository.findById(productRequest.getIvaId()).orElse(null);
        Category category = categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
        Brand brand = brandRepository.findById(productRequest.getBrandId()).orElse(null);

        if (productList.indexOf(product) == -1){
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.BAD_REQUEST);
        }

        if (productRequest.getProductNumber().isBlank() || productRequest.getProductNumber().isEmpty()){
            return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
        }

        if (productRequest.getProductNumber() != null) {
            for (Product productE : productList) {
                if (productE.getProductNumber().equals(productRequest.getProductNumber())) {
                    return new ResponseEntity<>("PN en uso", HttpStatus.BAD_REQUEST);
                }
                else{
                    product.setProductNumber(productRequest.getProductNumber().toUpperCase());
                }
            }
        }

        if (productRequest.getDescription().isEmpty() || productRequest.getDescription().isBlank() || productRequest.getDescription() == null){
            return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
        } else {
            product.setDescription(productRequest.getDescription());
        }

        if(productRequest.getCurrency().equals(null)){
            return new ResponseEntity<>("Seleccione una moneda",HttpStatus.BAD_REQUEST);
        }
        if(productRequest.getCurrency() != null){
            if(productRequest.getCurrency().equals(Currency.DOLAR)){
                product.setDolar(valueDolarCompany);
                product.setCurrency(Currency.DOLAR);
            }
            else{
                product.setDolar(null);
                product.setCurrency(Currency.PESO);
            }
        }

        if(productRequest.getCostPrice() == 0){
            return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
        }
        if(productRequest.getCostPrice() > 0){
            product.setCostPrice(productRequest.getCostPrice());
        }
        if(productRequest.getSalePrice() == 0){
            return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);
        }
        if(productRequest.getSalePrice() > 0){
            product.setSalePrice(productRequest.getSalePrice());
        }
        if(productRequest.getUtility() == 0){
            return new ResponseEntity<>("Ingrese una utilidad mayor a 0",HttpStatus.BAD_REQUEST);
        }
        if(productRequest.getUtility() > 0){
            product.setUtility(productRequest.getUtility());
        }
        if(productRequest.getIvaId().equals(null) || productRequest.getIvaId() == null){
            return new ResponseEntity<>("Debe seleccionar un valor de IVA para el producto",HttpStatus.BAD_REQUEST);
        }
        if (iva != null){
            if (product.getIva() != iva){
                    product.setIva(iva);
            }
        }
        if(productRequest.getCategoryId().equals(null) || productRequest.getCategoryId() == null){
            return new ResponseEntity<>("Debe seleccionar una categoria para el producto",HttpStatus.BAD_REQUEST);
        }
        if (category!= null){
            if (product.getCategory() != category){
                product.setCategory(category);
            }
        }
        if(productRequest.getBrandId().equals(null) || productRequest.getBrandId() == null){
            return new ResponseEntity<>("Debe seleccionar una marca para el producto",HttpStatus.BAD_REQUEST);
        }
        if (brand!= null){
            if (product.getBrand() != brand){
                product.setBrand(brand);
            }
        }
        productRepository.save(product);
        return new ResponseEntity<>(productMapper.toDTO(product),HttpStatus.OK);
    }

}
