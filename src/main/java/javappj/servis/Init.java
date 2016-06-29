package javappj.servis;

import javappj.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by The CAT on 28.6.2016.
 */

@Transactional
@Component
@Profile({"mongo", "mysql"})
public class Init implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(Init.class);

    @Autowired
    private UserRep userRep;

    @Autowired
    private ComentRep comentRep;

    @Autowired
    private ImageRep imageRep;

    @Autowired
    private MyTagRep tagRep;

    @Override
    public void afterPropertiesSet() throws Exception {


        log.debug("Loading data to database");
        System.out.println("INIT2 Users");
        initUsers();
        System.out.println("INIT2 Tags");
        initTags();
        System.out.println("INIT2 Images");
        initImages();
        System.out.println("INIT2 Coments");
        initComments();
        System.out.println("INIT konec");
        log.debug("Loading finished");
    }

    private boolean initUsers() throws IOException {
        boolean isEmpty = userRep.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/init/users.txt")))) {
                List<User> userList = read.lines().map(a -> new User(UUID.randomUUID(), a))
                        .collect(Collectors.toList());
                userRep.save(userList);
            }
        }
        return isEmpty;
    }

    private boolean initTags() throws IOException {
        boolean isEmpty = tagRep.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/init/tag.txt")))) {
                List<MyTag> tagsList = read.lines().map(s -> s.split(";"))
                        .map(t -> new MyTag(UUID.randomUUID(), t[0])).collect(Collectors.toList());

                tagRep.save(tagsList);
            }
        }
        return isEmpty;
    }

    private boolean initImages() throws IOException {
        boolean isEmpty = imageRep.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/init/image.txt")))) {
                List<Image> imagesList = read.lines().map(s -> s.split(";"))
                        .map(i -> new Image(UUID.randomUUID(), i[0], i[1], userRep.findByName(i[2]).get(0),
                                getComentSetFromString(i[3]), getTagSetFromString(i[4]))).collect(Collectors.toList());
                imageRep.save(imagesList);
            }
        }
        return isEmpty;
    }

    private boolean initComments() throws IOException {
        boolean isEmpty = comentRep.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/init/comment.txt")))) {
                List<Coment> commentsList = read.lines().map(s -> s.split(";"))
                        .map(c -> new Coment(UUID.fromString(c[0]), c[1], userRep.findByName(c[2]).get(0),
                                imageRep.findByName(c[3]).get(0))).collect(Collectors.toList());

                comentRep.save(commentsList);
            }
        }
        return isEmpty;
    }

    private Set<Coment> getComentSetFromString(String arrayInString) {
        HashSet<Coment> result = new HashSet<>();
        for (String s : arrayInString.split(",")) {
            if (!s.isEmpty()) {
                result.add(new Coment(UUID.fromString(s)));
            }
        }
        return result;
    }

    private Set<MyTag> getTagSetFromString(String arrayInString) {
        HashSet<MyTag> result = new HashSet<>();
        for (String s : arrayInString.split(",")) {
            if (!s.isEmpty()) {
                result.addAll(tagRep.findByValue(s));
            }
        }
        return result;
    }

    private Set<Image> getImageSetFromString(String arrayInString) {
        HashSet<Image> result = new HashSet<>();
        String[] strings = arrayInString.split(",");
        for (int i = 0; i < strings.length / 2; i++) {
            if (!strings[i * 2].isEmpty() && !strings[i * 2 + 1].isEmpty()) {
                result.add(new Image(UUID.fromString(strings[i * 2 + 1]), strings[i * 2]));
            }
        }
        return result;
    }
}
