package esdi.Services.repositories;

import esdi.Services.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByDni(String dni);

    public User findByUser(String user);

//    public User findByUserName(String user);
}
