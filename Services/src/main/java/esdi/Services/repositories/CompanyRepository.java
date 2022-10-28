package esdi.Services.repositories;

import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CompanyRepository extends JpaRepository<Company, Long> {

    public Company findByCuit(String cuit);
    public Company findByEmail(String email);
    public Company findByUser(String user);
    public Company findByStaffs(Staff staff);

}