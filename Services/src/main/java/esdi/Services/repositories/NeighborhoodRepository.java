package esdi.Services.repositories;

import esdi.Services.models.users.Company;
import esdi.Services.models.users.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface NeighborhoodRepository extends JpaRepository<Neighborhood,Long> {
    public List<Neighborhood> findAllByCompany(Company company);
}
