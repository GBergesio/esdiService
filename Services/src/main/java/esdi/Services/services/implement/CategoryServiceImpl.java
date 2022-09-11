package esdi.Services.services.implement;
import esdi.Services.dtos.CategoryDTO;
import esdi.Services.mappers.CategoryMapper;
import esdi.Services.models.products.Category;
import esdi.Services.repositories.CategoryRepository;
import esdi.Services.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;

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
    public ResponseEntity<?> createCategory(CategoryDTO categoryDTO) {

        try{

            if (categoryDTO.getNameCategory().equals(null) || categoryDTO.getNameCategory().isEmpty() || categoryDTO.getNameCategory().isBlank())
                return new ResponseEntity<>("Ingrese un nombre para la categoria",HttpStatus.BAD_REQUEST);

        Category category = new Category();
        category.setNameCategory(categoryDTO.getNameCategory());

        return new ResponseEntity<>(this.saveCategory(category),HttpStatus.CREATED);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> renameCategory(Long id, String name) {

        if (name.isEmpty() || name.isBlank() || name.equals(null))
            return new ResponseEntity<>("Ingrese un nombre valido",HttpStatus.BAD_REQUEST);

        if (categoryRepository.findByNameCategory(name) != null)
            return new ResponseEntity<>("Nombre de categoria en uso",HttpStatus.BAD_REQUEST);

        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null)
            return new ResponseEntity<>("Categoria no encontrada",HttpStatus.BAD_REQUEST);

        if (category.getNameCategory().equals(name))
            return new ResponseEntity<>("Asignar un nombre distinto",HttpStatus.BAD_REQUEST);

        category.setNameCategory(name);

        return new ResponseEntity<>(this.saveCategory(category),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        return null;
    }

}

