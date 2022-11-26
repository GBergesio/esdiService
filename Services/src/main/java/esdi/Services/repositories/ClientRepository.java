package esdi.Services.repositories;

import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByDni(String dni);

    public Client findByUser(String user);

    public Client findByEmail(String email);

    public List<Client> findAllByCompany(Company Company);
    public Client findByCompany(Company company);
}
