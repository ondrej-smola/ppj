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
@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImageRep extends CrudRepository<Image, UUID> {

     List<Image> findByName(String name);

     List<Image> findAll();

     Image findFirstByOrderById();

     Image findFirstByIdGreaterThanOrderById(UUID id);


    List<Image> findBytagSetValue(String tag);

      List<Image> findByNameContaining(String name);
     List<Image> findByUser(User user);



}
