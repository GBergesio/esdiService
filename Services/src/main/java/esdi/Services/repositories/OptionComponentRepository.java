package esdi.Services.repositories;

import esdi.Services.models.budgets.OptionComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OptionComponentRepository extends JpaRepository<OptionComponent,Long> {
}
