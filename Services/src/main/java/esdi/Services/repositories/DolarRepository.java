package esdi.Services.repositories;

import esdi.Services.models.Dolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DolarRepository extends JpaRepository<Dolar,Long> {
}