package esdi.Services.repositories;

import esdi.Services.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByDni(String dni);

    public Client findByUser(String user);


}
