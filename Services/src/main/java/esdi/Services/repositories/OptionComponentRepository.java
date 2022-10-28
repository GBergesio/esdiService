package esdi.Services.repositories;

import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OptionComponentRepository extends JpaRepository<OptionComponent,Long> {
    public List<OptionComponent> findAllByCompany(Company company);
}
