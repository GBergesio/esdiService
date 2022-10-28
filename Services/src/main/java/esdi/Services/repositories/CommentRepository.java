package esdi.Services.repositories;

import esdi.Services.models.Comment;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment,Long> {

    public List<Comment> findAllByCompany(Company company);
}
