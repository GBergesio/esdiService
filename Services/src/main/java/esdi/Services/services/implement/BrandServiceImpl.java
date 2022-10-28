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
    public ResponseEntity<?> allBrandsByCompany(Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        List<Brand> brandsByCompany = brandRepository.findAllByCompany(company);

        return new ResponseEntity<>(brandMapper.toDTO(brandsByCompany), HttpStatus.OK);
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
    public ResponseEntity<?> createBrand(BrandDTO brandDTO) {
        try{

            if (brandDTO.getNameBrand().equals(null) || brandDTO.getNameBrand().isEmpty() || brandDTO.getNameBrand().isBlank())
                return new ResponseEntity<>("Ingrese un nombre para la categoria",HttpStatus.BAD_REQUEST);

            if (brandRepository.findByNameBrand(brandDTO.getNameBrand()) != null)
                return new ResponseEntity<>("Nombre de marca en uso",HttpStatus.BAD_REQUEST);

            Brand brand = new Brand();
            brand.setNameBrand(brandDTO.getNameBrand());

            return new ResponseEntity<>(this.saveBrand(brand),HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> renameBrand(Long id, String name) {
        if (name.isEmpty() || name.isBlank() || name.equals(null))
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);

        if (brandRepository.findByNameBrand(name) != null)
            return new ResponseEntity<>("Nombre de marca en uso",HttpStatus.BAD_REQUEST);

        Brand brand  = brandRepository.findById(id).orElse(null);

        if (brand == null)
            return new ResponseEntity<>("Marca no encontrada",HttpStatus.BAD_REQUEST);

        if (brand.getNameBrand().equals(name))
            return new ResponseEntity<>("Asignar un nombre distinto",HttpStatus.BAD_REQUEST);

        brand.setNameBrand(name);

        return new ResponseEntity<>(this.saveBrand(brand),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBrand(Long id) {
        Brand brand  = brandRepository.findById(id).orElse(null);
        List<Product> products = productRepository.findAll().stream().filter(product -> product.getBrand().getId() == id).collect(Collectors.toList());

        if (products.size() >= 1)
            return new ResponseEntity<>("No se puede borrar ya que la marca está asignada a un producto",HttpStatus.BAD_REQUEST);

        if (brand == null)
            return new ResponseEntity<>("No existe marca",HttpStatus.BAD_REQUEST);

        brandRepository.delete(brand);
        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }
}
