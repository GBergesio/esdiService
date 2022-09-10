package esdi.Services.repositories;

import esdi.Services.models.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin,Long>{


    public Admin findByDni(String dni);

    public Admin findByUser(String user);
}
