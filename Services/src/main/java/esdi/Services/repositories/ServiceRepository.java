package esdi.Services.repositories;
import esdi.Services.models.products.Category;
import esdi.Services.models.products.ServiceArt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ServiceRepository extends JpaRepository<ServiceArt,Long> {

    public List<ServiceArt> findByCategory(Category category);
}
