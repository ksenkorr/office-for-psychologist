package db;

import model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "user-api", itemResourceRel = "user-api", path = "user-api")
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


}
