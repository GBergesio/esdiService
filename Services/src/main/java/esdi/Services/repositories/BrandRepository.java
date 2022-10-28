package esdi.Services.repositories;

import esdi.Services.models.products.Brand;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BrandRepository extends JpaRepository<Brand,Long> {

    Brand findByNameBrand(String name);

    public List<Brand> findAllByCompany(Company company);
}

