package esdi.Services.repositories;

import esdi.Services.models.Iva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IvaRepository extends JpaRepository<Iva,Long> {
}
