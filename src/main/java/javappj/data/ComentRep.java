package javappj.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by The CAT on 27.6.2016.
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface ComentRep extends CrudRepository<Coment, UUID> {
}
