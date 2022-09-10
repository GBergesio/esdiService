package esdi.Services.repositories;
import esdi.Services.models.products.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByNameCategory(String name);
}
