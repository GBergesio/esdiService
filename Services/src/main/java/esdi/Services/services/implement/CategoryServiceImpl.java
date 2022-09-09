package esdi.Services.services.implement;
import esdi.Services.dtos.CategoryDTO;
import esdi.Services.mappers.CategoryMapper;
import esdi.Services.models.products.Category;
import esdi.Services.repositories.CategoryRepository;
import esdi.Services.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}

