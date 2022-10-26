package esdi.Services.repositories;

import esdi.Services.models.users.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StaffRepository extends JpaRepository<Staff, Long> {
    public Staff findByDni(String dni);
    public Staff findByUser(String user);
    public Staff findByEmail(String email);

}