package esdi.Services.repositories;

import esdi.Services.models.Dolar;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DolarRepository extends JpaRepository<Dolar,Long> {
    public Dolar findByCompany(Company company);
}
