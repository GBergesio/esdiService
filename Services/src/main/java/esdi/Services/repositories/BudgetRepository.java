package esdi.Services.repositories;

import esdi.Services.models.budgets.Budget;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    public List<Budget> findAllByCompany(Company company);
}
