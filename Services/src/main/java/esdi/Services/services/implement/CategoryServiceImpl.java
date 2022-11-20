package esdi.Services.services.implement;
import esdi.Services.dtos.CategoryDTO;
import esdi.Services.mappers.CategoryMapper;
import esdi.Services.models.products.Brand;
import esdi.Services.models.products.Category;
import esdi.Services.models.products.Product;
import esdi.Services.models.products.ServiceArt;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CategoryRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.ProductRepository;
import esdi.Services.repositories.ServiceRepository;
import esdi.Services.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryDTO getCategoryDTO(Category category) {
        return categoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> findAllDTO() {
        return categoryMapper.toDTO(categoryRepository.findAll());
    }

    @Override
    public ResponseEntity<?> allCategories() {
        return new ResponseEntity<>(findAllDTO(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (!categoryRepository.existsById(id)){
            return new ResponseEntity<>("No se encontró categoria con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoryRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allCategoriesByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Category> categoriesByCompany = categoryRepository.findAllByCompany(company);
        List<Category> categoriesAvailable = categoriesByCompany.stream().filter(c -> c.getDeleted().equals(false)).collect(Collectors.toList());
        return new ResponseEntity<>(categoryMapper.toDTO(categoriesAvailable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createCategory(CategoryDTO categoryDTO, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Category> categories = categoryRepository.findAllByCompany(company);
        Boolean categoryExists = categories.stream().anyMatch(c -> c.getNameCategory().equals(categoryDTO.getNameCategory()));
        try{

            if (categoryDTO.getNameCategory().equals(null) || categoryDTO.getNameCategory().isEmpty() || categoryDTO.getNameCategory().isBlank()) {
                return new ResponseEntity<>("Ingrese un nombre para la categoria", HttpStatus.BAD_REQUEST);
            }
            if(categoryExists){
                return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
            }

            Category category = new Category();
            category.setNameCategory(categoryDTO.getNameCategory());
            category.setDeleted(false);
            category.setCompany(company);

            categoryRepository.save(category);
            return new ResponseEntity<>("Categoria creada correctamente",HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> renameCategory(Long id, String name, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Category> categories = categoryRepository.findAllByCompany(company);
        Boolean categoryExists = categories.stream().anyMatch(c -> c.getNameCategory().equals(name));

        if (name.isEmpty() || name.isBlank() || name.equals(null))
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);

        if(categoryExists){
            return new ResponseEntity<>("Nombre en uso",HttpStatus.BAD_REQUEST);
        }

        Category category = categoryRepository.findById(id).orElse(null);

        if (categories.indexOf(category) == -1)
            return new ResponseEntity<>("Categoria no encontrada",HttpStatus.BAD_REQUEST);

        category.setNameCategory(name);
        categoryRepository.save(category);

        return new ResponseEntity<>("Categoria modificada correctamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Category category = categoryRepository.findById(id).orElse(null);
        List<Category> categories = categoryRepository.findAllByCompany(company);
        List<Product> products = productRepository.findAllByCompany(company);
        Boolean existInProduct = products.stream().anyMatch(product -> product.getCategory().equals(category));

        if (categories.indexOf(category) == -1)
            return new ResponseEntity<>("Categoria no encontrada",HttpStatus.BAD_REQUEST);

        if (existInProduct){
            category.setDeleted(true);
            categoryRepository.save(category);
            return new ResponseEntity<>("La categoria existe en algún producto. Categoria desactivada correctamente",HttpStatus.OK);
        }

        category.setDeleted(true);
        categoryRepository.save(category);

        return new ResponseEntity<>("Categoria eliminada correctamente",HttpStatus.OK);
    }

}

