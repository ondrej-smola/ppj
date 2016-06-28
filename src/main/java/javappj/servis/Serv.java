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
    private ImageRep imageRepository;

    @Autowired
    private ComentRep commentRepository;

    @Autowired
    private UserRep authorRepository;

    public List<Image> getImagesByName(String name) {
        if (name == null) {
            return null;
        }

        List<Image> images = imageRepository.findByName(name);
        Hibernate.initialize(images);

        if (images.size() == 0) {
            return null;
        }

        return images;
    }
    public List<Image> getImagesByTag(String tag) {
        if (tag == null) {
            return null;
        }

        List<Image> images = imageRepository.findByTagSetIsIn(tag);
        Hibernate.initialize(images);

        if (images.size() == 0) {
            return null;
        }

        return images;
    }
    public List<Image> getImagesByAuthor(String user) {
        if (user == null) {
            return null;
        }

        List<Image> images = imageRepository.findByUserName(user);
        Hibernate.initialize(images);

        if (images.size() == 0) {
            return null;
        }

        return images;
    }



    public Image getImageById(UUID id) {
        if (id == null) {
            return null;
        }
        return imageRepository.findOne(id);
    }

    public Image getFirstImage() {
        return imageRepository.findFirstByOrderById();
    }

    public Image getNextImageById(UUID prevId) {
        return imageRepository.findFirstByIdGreaterThanOrderById(prevId);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void LikeImg(UUID id) {
        Image image = imageRepository.findOne(id);
        image.Like();
        imageRepository.save(image);
    }
    public void LikeComent(UUID id) {
        Coment coment = commentRepository.findOne(id);
        coment.Like();
        commentRepository.save(coment);
    }

    public void DislikeComent(UUID id) {
        Coment coment = commentRepository.findOne(id);
        coment.Dislike();
        commentRepository.save(coment);
    }


    public void DislikeImg(UUID id) {
        Image image = imageRepository.findOne(id);
        image.Dislike();
        imageRepository.save(image);
    }

    public void addComment(Image image, String text,  String username) {
        User user = (User) authorRepository.findByName(username);
        commentRepository.save(new Coment(UUID.randomUUID(),text, user,image));
    }
}

