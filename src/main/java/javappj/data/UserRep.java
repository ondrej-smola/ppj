package javappj.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by The CAT on 27.6.2016.
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRep extends CrudRepository<User, UUID> {

    public List<User> findByName(String name);


}