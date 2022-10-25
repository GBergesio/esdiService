package esdi.Services.repositories;

import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CompanyRepository extends JpaRepository<Company, Long> {

    public Company findByCuit(String cuit);
}
