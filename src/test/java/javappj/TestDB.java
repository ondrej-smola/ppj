package javappj;

import javappj.data.*;
import javappj.servis.Serv;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@Transactional
public class TestDB {
    @Autowired
    private UserRep authorRepository;

    @Autowired
    private ComentRep commentRepository;

    @Autowired
    private ImageRep imageRepository;
    @Autowired
    private MyTagRep tagRepository;

    @Autowired
    private Serv serv;

   /* @Autowired
    private TagRepository tagRepository;
*/

    private User user1;
    private User user2;
    private User user3;

    private Coment coment1;
    private Coment coment2;
    private Coment coment3;

    private MyTag tag1;
    private MyTag tag2;
    private MyTag tag3;

    private Image image1;
    private Image image2;
    private Image image3;


    private UUID user1ID = UUID.randomUUID();
    private UUID user2ID = UUID.randomUUID();
    private UUID user3ID = UUID.randomUUID();

    private UUID coment1ID = UUID.randomUUID();
    private UUID coment2ID = UUID.randomUUID();
    private UUID coment3ID = UUID.randomUUID();

    private UUID image1ID = UUID.randomUUID();
    private UUID image2ID = UUID.randomUUID();
    private UUID image3ID = UUID.randomUUID();

    private UUID tag1ID = UUID.randomUUID();
    private UUID tag2ID = UUID.randomUUID();
    private UUID tag3ID = UUID.randomUUID();


    @Before
    public void init() {
        imageRepository.deleteAll();
        tagRepository.deleteAll();
        commentRepository.deleteAll();
        authorRepository.deleteAll();
        initDBData();
    }

    private void initDBData() {
        saveAuthors();
        saveTags();
        saveImages();
        saveComments();
    }

    private void saveAuthors() {
        user1 = new User(user1ID, "Alice");
        user2 = new User(user2ID, "Božena");
        user3 = new User(user3ID, "Cecilie");


        HashSet<User> authors = new HashSet<>();
        authors.add(user1);
        authors.add(user2);
        authors.add(user3);
        authorRepository.save(authors);
    }

    private void saveComments() {
        coment1 = new Coment(coment1ID, "Test1", new User(user1ID), new Image(image1ID));
        coment2 = new Coment(coment2ID, "Test2", new User(user2ID), new Image(image2ID));
        coment3 = new Coment(coment3ID, "Test3", new User(user3ID), new Image(image3ID));

        HashSet<Coment> comments = new HashSet<>();
        comments.add(coment1);
        comments.add(coment2);
        comments.add(coment3);

        commentRepository.save(comments);
    }

    private void saveImages() {
        HashSet<Coment> commentSet1 = new HashSet<>();
        commentSet1.add(new Coment(coment1ID));

        HashSet<MyTag> tagsSet1 = new HashSet<>();
        tagsSet1.add(tag1);
        tagsSet1.add(tag2);
        image1 = new Image(image1ID, "image1", "url://something/1", new User(user1ID), commentSet1, tagsSet1);

        HashSet<Coment> commentSet2 = new HashSet<>();
        commentSet2.add(new Coment(coment2ID));
        image2 = new Image(image2ID, "image2", "url://something/2", new User(user2ID), commentSet2, tagsSet1);

        HashSet<MyTag> tagsSet2 = new HashSet<>();
        tagsSet2.add(tag3);

        HashSet<Coment> commentSet3 = new HashSet<>();
        commentSet3.add(new Coment(coment3ID));
        image3 = new Image( image3ID, "image3", "url://something/3", new User(user3ID), commentSet3, tagsSet2);

        HashSet<Image> images = new HashSet<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        imageRepository.save(images);
    }

    private void saveTags() {
        HashSet<Image> imageSet1 = new HashSet<>();
        imageSet1.add(new Image("image1"));
        imageSet1.add(new Image("image2"));
        tag1 = new MyTag(tag1ID, "tag1", imageSet1);
        tag2 = new MyTag(tag2ID, "tag2", imageSet1);

        HashSet<Image> imageSet2 = new HashSet<>();
        imageSet1.add(new Image("image3"));
        tag3 = new MyTag(tag3ID, "tag3", imageSet2);

        HashSet<MyTag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tagRepository.save(tags);
    }

    @Test
    public void testGetByName() {
        List<Image> foundImages1 = serv.getImagesByName("image1");
        assertArrayEquals(new Image[]{image1},  foundImages1.toArray(new Image[foundImages1.size()]));

        List<Image> foundImages2 = serv.getImagesByName("image2");
        assertNotEquals("Results of second search should not match", new Image[]{image1},foundImages2.toArray(new Image[foundImages2.size()]));
    }



    @Test
    public void searchImageByAutor() {
      //  User testAutor = new User(UUID.randomUUID(),"Test autor 3");
        assertTrue( serv.findByImageUser(user1).size()==1);

      //  Image commentImage = serv.addNewImage("Test image", testAutor, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Cc-zero.svg/2000px-Cc-zero.svg.png", Arrays.asList("logo", "tag", "test"));
        System.out.println("author1ID");
        assertEquals( "Alice", serv.findByImageUser(user1).get(0).getUser().getName());
    }

    // výběr podle autora
    @Test
    public void searchImageByTag() {
     //   User testAutor = new User("Test user 4");
     //   Image commentImage = imageService.addNewImage("Test image", testAutor, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Cc-zero.svg/2000px-Cc-zero.svg.png", Arrays.asList("logo", "tag", "test"));

        assertTrue(serv.findByImageTags( "").isEmpty() );
        assertTrue( serv.findByImageTags("quak").isEmpty() );
        System.out.println("TEST TTTTTTTTTTTTTTTTTTTTTTTTTTT "+serv.findByImageTags("tag1").get(0).getName());
        assertEquals("image3", serv.findByImageTags("tag3").get(0).getName());
        assertTrue(serv.findByImageTags( "tag").isEmpty());
    }
   /*
    @Test
    public void testGetByTag() {
        List<Image> foundImages1 = serv.getImagesByTag("tag3");
        assertArrayEquals("Results of first search should match", new Image[]{image3},
                foundImages1.toArray(new Image[foundImages1.size()]));

        List<Image> foundImages2 = serv.getImagesByTag("tag3");
        assertNotEquals("Results of second search should not match", new Image[]{image1, image2},
                foundImages2.toArray(new Image[foundImages2.size()]));
    }

    @Test
    public void testGetByAuthor() {
        List<Image> foundImages1 = serv.getImagesByUser("adam");
        assertArrayEquals("Results of first search should match", new Image[]{image1},
                foundImages1.toArray(new Image[foundImages1.size()]));

        List<Image> foundImages2 = serv.getImagesByUser("bozena");
        assertNotEquals("Results of second search should not match", new Image[]{image1},
                foundImages2.toArray(new Image[foundImages2.size()]));
    }*/
}
