package esdi.Services.repositories;
import esdi.Services.models.products.ServiceArt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ServiceRepository extends JpaRepository<ServiceArt,Long> {
}
