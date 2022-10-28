package esdi.Services.services.implement;
import esdi.Services.dtos.ProductDTO;
import esdi.Services.dtos.request.ProductRequest;
import esdi.Services.mappers.ProductMapper;
import esdi.Services.models.Currency;
import esdi.Services.models.Dolar;
import esdi.Services.models.products.Brand;
import esdi.Services.models.products.Category;
import esdi.Services.models.products.Iva;
import esdi.Services.models.products.Product;
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
        Company company = companyRepository.findByUser(authentication.getName());
        List<Product> productsByCompany = productRepository.findAllByCompany(company);

        return new ResponseEntity<>(productMapper.toDTO(productsByCompany), HttpStatus.OK);
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
    public ResponseEntity<?> createProduct(ProductRequest productRequest) {

        try{
            Iva iva = ivaRepository.findById(productRequest.getIvaId()).orElse(null);
            Category category = categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
            Brand brand = brandRepository.findById(productRequest.getBrandId()).orElse(null);
            Dolar dolar = dolarRepository.findById(productRequest.getDolarId()).orElse(null);

            if (iva == null)
                return new ResponseEntity<>("Ingrese Iva",HttpStatus.BAD_REQUEST);

            if (category == null)
                return new ResponseEntity<>("Ingrese categoria",HttpStatus.BAD_REQUEST);

            if (brand == null)
                return new ResponseEntity<>("Ingrese marca",HttpStatus.BAD_REQUEST);

            if (productRequest.getCurrency().equals(Currency.DOLAR)){
                assert dolar != null;
                productRequest.setSalePrice(((productRequest.getCostPrice() * dolar.getPrice()) * iva.getIva()) * productRequest.getUtility());
            }

            if (productRequest.getCurrency().equals(Currency.PESO)){
                productRequest.setSalePrice((productRequest.getCostPrice() * iva.getIva()) * productRequest.getUtility());
            }

            Product product = new Product();
            product.setProductNumber(productRequest.getProductNumber().toUpperCase());
            product.setDescription(productRequest.getDescription());
            product.setCostPrice(productRequest.getCostPrice());
            product.setSalePrice(productRequest.getSalePrice());
            product.setUtility(productRequest.getUtility());
            product.setCurrency(productRequest.getCurrency());

            product.setIva(iva);
            product.setCategory(category);
            product.setBrand(brand);

            if (dolar != null)
                product.setDolar(dolar.getPrice());

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
    public ResponseEntity<?> updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null){
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.BAD_REQUEST);
        }

        Iva iva = ivaRepository.findById(productRequest.getIvaId()).orElse(null);
        Long ivaId = product.getIva().getId();
        double ivaProduct = product.getIva().getIva();
        double dolarProduct = product.getDolar();
        double precioVenta = productRequest.getSalePrice();
        double utilidad = productRequest.getSalePrice() - (product.getCostPrice() * ivaProduct);
        double utilidadDolar = productRequest.getSalePrice() - ((product.getCostPrice() * ivaProduct) * dolarProduct);
        double margenDeGanancia = utilidad / precioVenta;
        double margenDeGananciaDolar = utilidadDolar / precioVenta;
        double nuevoPrecioCambioIva = (productRequest.getCostPrice() * iva.getIva() * productRequest.getUtility());

        if (productRequest.getProductNumber() != null){
            if (productRepository.findByProductNumber(productRequest.getProductNumber().toUpperCase()) != null)
                return new ResponseEntity<>("Codigo de producto en uso", HttpStatus.BAD_REQUEST);
            if (productRequest.getProductNumber().isBlank() || productRequest.getProductNumber().isEmpty())
                return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
            if (!product.getProductNumber().equals(productRequest.getProductNumber().toUpperCase()))
                product.setProductNumber(productRequest.getProductNumber().toUpperCase());
        }

        if (productRequest.getDescription() != null)
            if (productRequest.getDescription().isEmpty() || productRequest.getDescription().isBlank() || productRequest.getDescription() == null)
                return new ResponseEntity<>("Este campo no puede estar vacio", HttpStatus.BAD_REQUEST);
            if (!product.getDescription().equals(productRequest.getDescription()))
                product.setDescription(productRequest.getDescription());

        if (productRequest.getCostPrice() > 0){
            if (product.getCostPrice() != productRequest.getCostPrice())
                if (product.getCurrency().equals(Currency.DOLAR)) {
                    product.setCostPrice(productRequest.getCostPrice());
                    product.setSalePrice(((productRequest.getCostPrice() * ivaProduct) * dolarProduct) * productRequest.getUtility());
                    product.setUtility(productRequest.getUtility());
                }
                else {
                    product.setCostPrice(productRequest.getCostPrice());
                    product.setSalePrice(((productRequest.getCostPrice() * ivaProduct)) * productRequest.getUtility());
                    product.setUtility(productRequest.getUtility());
                }
        }
        else return new ResponseEntity<>("Ingrese un monto mayor a 0",HttpStatus.BAD_REQUEST);

        if (productRequest.getUtility() > 0){
            if (product.getUtility() != productRequest.getUtility())
                if (product.getCurrency().equals(Currency.DOLAR)) {
                    product.setUtility(productRequest.getUtility());
                    product.setSalePrice(((productRequest.getCostPrice() * ivaProduct) * dolarProduct) * productRequest.getUtility());
                }
                if (product.getCurrency().equals(Currency.PESO)) {
                    product.setUtility(productRequest.getUtility());
                    product.setSalePrice((productRequest.getCostPrice() * ivaProduct) * productRequest.getUtility());
                }
        }
        else return new ResponseEntity<>("Ingrese un valor mayor a 0",HttpStatus.BAD_REQUEST);

//        if (productRequest.getSalePrice() > 0){
//            if (product.getSalePrice() != productRequest.getSalePrice())
//                if (product.getCurrency().equals(Currency.DOLAR)) {
////                    product.setUtility(margenDeGananciaDolar);
//                    product.setSalePrice(productRequest.getSalePrice());
//                }
////            if (product.getCurrency().equals(Currency.PESO)) {
////                product.setSalePrice(productRequest.getSalePrice());
////                product.setUtility(margenDeGanancia);
////            }
//        }
//        else return new ResponseEntity<>("Ingrese un valor mayor a 0",HttpStatus.BAD_REQUEST);
//

        if (product.getCurrency() != productRequest.getCurrency()){
            if (productRequest.getCurrency().equals(Currency.PESO)){
                product.setCurrency(productRequest.getCurrency());
                product.setUtility(productRequest.getUtility());
                product.setSalePrice((productRequest.getCostPrice() * ivaProduct) * productRequest.getUtility());
            }
            if (productRequest.getCurrency().equals(Currency.DOLAR)){
                product.setCurrency(productRequest.getCurrency());
                product.setUtility(productRequest.getUtility());
                product.setSalePrice(((productRequest.getCostPrice() * ivaProduct) * dolarProduct) * productRequest.getUtility());
            }
        }

// verificar xq tira 500 cuando pongo null
        if (iva != null){
            if (product.getIva() != iva){
                if (product.getCurrency().equals(Currency.DOLAR)){
                    product.setIva(iva);
                    product.setSalePrice(((productRequest.getCostPrice() * iva.getIva() * dolarProduct) * productRequest.getUtility()));
                }
                if (product.getCurrency().equals(Currency.PESO)){
                    product.setIva(iva);
                    product.setSalePrice((productRequest.getCostPrice() * iva.getIva() * productRequest.getUtility()));
                }
            }
        }
        productRepository.save(product);
        return new ResponseEntity<>(productMapper.toDTO(product),HttpStatus.OK);
    }


}


