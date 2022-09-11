package esdi.Services.repositories;

import esdi.Services.models.users.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByDni(String dni);
    Staff findByUser(String user);
    Staff findByEmail(String email);
}
