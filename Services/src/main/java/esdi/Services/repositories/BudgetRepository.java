package esdi.Services.repositories;

import esdi.Services.models.budgets.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BudgetRepository extends JpaRepository<Budget,Long> {
}
