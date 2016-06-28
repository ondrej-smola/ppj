package javappj.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    public List<Image> findByName(String name);

    public List<Image> findAll();

    public Image findFirstByOrderById();

    public Image findFirstByIdGreaterThanOrderById(UUID id);

    @Query("select Image from Image  where Image.User.name=:user")
    public List<Image> findByUserName(@Param("user") String user);

    @Query("select Image from Image  inner join Image.tagSet tags where tags.value = :tag")
    public List<Image> findByTagSetIsIn(@Param("mytag") String mytag);
}
