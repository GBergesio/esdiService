package esdi.Services.repositories;

import esdi.Services.models.products.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BrandRepository extends JpaRepository<Brand,Long> {
}

