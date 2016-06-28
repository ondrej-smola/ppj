package javappj.servis;

import javappj.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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


        log.debug("Loading...");


        String content = readFile("test.txt", StandardCharsets.UTF_8);
        String[] stringy=content.split("@");
        List<User> users= new ArrayList<User>();
        String[] us = stringy[0].split("\\n");
        for(int index = 0; index < us.length; index++) {

            User u=new User(us[index]);
            users.add(u);

        }
        userRep.save( users);
        String[] tags = stringy[1].split("\\n");
        List<MyTag> tag= new ArrayList<MyTag>();
        for(int index = 0; index < us.length; index++) {

            MyTag t=new MyTag(tags[index]);
            tag.add(t);

        }
        tagRep.save( tag);

        String[] imgs = stringy[2].split("\\n");
        List<Image> ing= new ArrayList<Image>();
        for(int index = 0; index < us.length; index++) {
            String[] a=imgs[index].split(";");
           tags = a[4].split(",");
            Set<MyTag> ta= new HashSet<MyTag>();
            for(int i = 0; i < us.length; i++) {

                MyTag t=new MyTag(tags[index]);
                ta.add(t);

            }
            Image i=new Image( UUID.fromString(a[3]),a[0],a[1],userRep.findByName(a[2]).get(0),new HashSet<Coment>(),ta);
            ing.add(i);

        }
        imageRep.save( ing);





        log.debug("Finished");
    }
    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}