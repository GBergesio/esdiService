package esdi.Services.repositories;
import esdi.Services.models.products.Category;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);

    public List<Category> findAllByCompany(Company company);

}
