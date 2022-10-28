package esdi.Services.repositories;

import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OptionBudgetRepository extends JpaRepository<OptionBudget,Long> {
    public List<OptionBudget> findAllByCompany(Company company);
}
