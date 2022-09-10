package esdi.Services.repositories;

import esdi.Services.models.users.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TechnicianRepository extends JpaRepository <Technician,Long> {

    public Technician findByUser(String user);
    public Technician findByDni(String dni);
}
