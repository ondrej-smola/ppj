package javappj.servis;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javappj.data.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by The CAT on 27.6.2016.
 */
@Service
@Transactional
public class Serv {

    @Autowired
    private ImageRep imageRep;

    @Autowired
    private ComentRep comentRep;

    @Autowired
    private UserRep userRep;

    public List<Image> getImagesByName(String name) {
        if (name == null) {
            return null;
        }

        List<Image> images = imageRep.findByName(name);
        Hibernate.initialize(images);

        if (images.size() == 0) {
            return null;
        }

        return images;
    }





    public List<Image> findByImageUser(User user) {
        return imageRep.findByUser(user);
    }

   /* public List<Image> findByImageTags(List<String> tag) {
        return imageRepository.findByMyTagsIn(tag);
    }*/

    public List<Image> findByImageTags(String tag) {
        return imageRep.findBytagSetValue(tag);
    }

    public Image getImageById(UUID id) {
        if (id == null) {
            return null;
        }
        return imageRep.findOne(id);
    }

    public Image getFirstImage() {
        return imageRep.findFirstByOrderById();
    }

    public Image getNextImageById(UUID prevId) {
        return imageRep.findFirstByIdGreaterThanOrderById(prevId);
    }

    public List<Image> getAllImages() {
        return imageRep.findAll();
    }

    public void LikeImg(UUID id) {
        Image image = imageRep.findOne(id);
        image.Like();
        imageRep.save(image);
    }
    public void LikeComent(UUID id) {
        Coment coment = comentRep.findOne(id);
        coment.Like();
        comentRep.save(coment);
    }

    public void DislikeComent(UUID id) {
        Coment coment = comentRep.findOne(id);
        coment.Dislike();
        comentRep.save(coment);
    }


    public void DislikeImg(UUID id) {
        Image image = imageRep.findOne(id);
        image.Dislike();
        imageRep.save(image);
    }

    public void addComment(Image image, String text,  String username) {
        User user = (User) userRep.findByName(username);
        comentRep.save(new Coment(UUID.randomUUID(),text, user,image));
    }
}

