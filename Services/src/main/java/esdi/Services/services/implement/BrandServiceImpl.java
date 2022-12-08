package esdi.Services.services.implement;

import esdi.Services.dtos.BrandDTO;
import esdi.Services.mappers.BrandMapper;
import esdi.Services.models.products.Brand;
import esdi.Services.models.products.Category;
import esdi.Services.models.products.Product;
import esdi.Services.models.products.ServiceArt;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.BrandRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.ProductRepository;
import esdi.Services.repositories.ServiceRepository;
import esdi.Services.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public BrandDTO getBrandDTO(Brand brand) {
        return brandMapper.toDTO(brand);
    }

    @Override
    public List<BrandDTO> findAllDTO() {
        return brandMapper.toDTO(brandRepository.findAll());
    }

    @Override
    public ResponseEntity<?> allBrands() {
        return new ResponseEntity<>(findAllDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (!brandRepository.existsById(id)){
            return new ResponseEntity<>("No se encontró marca con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(brandRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allBrandsByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Brand> brandsByCompany = brandRepository.findAllByCompany(company);
        List<Brand> brandsAvailable = brandsByCompany.stream().filter(brand -> brand.getDeleted().equals(false)).collect(Collectors.toList());

        return new ResponseEntity<>(brandMapper.toDTO(brandsAvailable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createBrand(BrandDTO brandDTO, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Brand> brands = brandRepository.findAllByCompany(company);
        Boolean brandExists = brands.stream().anyMatch(brand -> brand.getName().equals(brandDTO.getName()));

        try{
            if (brandDTO.getName().equals(null) || brandDTO.getName().isEmpty() || brandDTO.getName().isBlank()){
                return new ResponseEntity<>("Ingrese un nombre para la categoria",HttpStatus.BAD_REQUEST);
            }
            if(brandExists){
                return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
            }
            Brand brand = new Brand();
            brand.setName(brandDTO.getName());
            brand.setDeleted(false);
            brand.setCompany(company);

            brandRepository.save(brand);
            return new ResponseEntity<>("Marca creada correctamente",HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> renameBrand(Long id, String name, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Brand> brands = brandRepository.findAllByCompany(company);
        Boolean brandExists = brands.stream().anyMatch(brand -> brand.getName().equals(name));

        if (name.isEmpty() || name.isBlank() || name.equals(null))
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);

        if(brandExists){
            return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
        }

        Brand brand  = brandRepository.findById(id).orElse(null);

        if (brands.indexOf(brand) == -1)
            return new ResponseEntity<>("Marca no encontrada",HttpStatus.BAD_REQUEST);

        brand.setName(name);
        brandRepository.save(brand);

        return new ResponseEntity<>("Marca modificada correctamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBrand(Long id, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Brand brand  = brandRepository.findById(id).orElse(null);
        List<Brand> brands = brandRepository.findAllByCompany(company);
        List<Product> products = productRepository.findAllByCompany(company);
        Boolean existInProduct = products.stream().anyMatch(product -> product.getBrand().equals(brand));

        if (brands.indexOf(brand) == -1)
            return new ResponseEntity<>("Marca no encontrada",HttpStatus.BAD_REQUEST);

        if (existInProduct){
            brand.setDeleted(true);
            brandRepository.save(brand);
            return new ResponseEntity<>("La marca existe en algún producto. Marca desactivada correctamente",HttpStatus.OK);
        }

        brand.setDeleted(true);
        brandRepository.save(brand);

        return new ResponseEntity<>("Marca eliminada exitosamente",HttpStatus.OK);
    }
}
