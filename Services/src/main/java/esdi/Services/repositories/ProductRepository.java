package esdi.Services.repositories;

import esdi.Services.models.products.Category;
import esdi.Services.models.products.Product;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findByProductNumber(String productNumber);

    public List<Product> findByCategory(Category category);

    public Optional<Product> findById(Long id);
    public List<Product> findAllByCompany(Company company);
}
