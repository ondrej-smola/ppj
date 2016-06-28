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
        System.out.println("INIT  SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");

        log.debug("Loading...");
        ClassLoader classLoader = getClass().getClassLoader();
        String content = readFile( classLoader.getResource("init/init.txt").getPath().substring(1).replace("%20"," "), StandardCharsets.UTF_8);
      //  String content = readFile("\\init\\init.txt", StandardCharsets.UTF_8);

        String[] stringy=content.split("@");
        List<User> users= new ArrayList<User>();
        String[] us = stringy[0].split(";");
    //   System.out.printf("INIT 2 :%s?%n", stringy[0]);
        for(int index = 0; index < us.length; index++) {
        // System.out.printf("INIT 2 :%s?%n", us[index]);
            User u=new User(UUID.randomUUID(),us[index].replaceAll("(\\r|\\n)", ""));
            users.add(u);

        }

        userRep.save( users);




        String[] tags = stringy[1].split("\\n");
        List<MyTag> tag= new ArrayList<MyTag>();
        for(int index = 0; index < us.length; index++) {

            MyTag t=new MyTag(UUID.randomUUID(),tags[index].replace("\\n",""));
            tag.add(t);

        }
        tagRep.save( tag);

        String[] imgs = stringy[2].split("\\n");
        List<Image> ing= new ArrayList<Image>();
        for(int index = 0; index < imgs.length; index++) {
            String[] a=imgs[index].split(";");
            if(a.length==5) {
              //  System.out.println("INIT :" + imgs[index]);
                tags = a[4].split(",");
                Set<MyTag> ta = new HashSet<MyTag>();
                for (int i = 0; i < tags.length; i++) {

                    MyTag t = new MyTag(UUID.randomUUID(),tags[i]);
                    ta.add(t);

                }
                Set<Coment> co = new HashSet<Coment>();
                for (String s : a[3].split(",")) {
                    if (!s.isEmpty()) {
                      //  System.out.println("INIT4 +"+s+"!");
                        co.add(new Coment(UUID.fromString(s)));
                     //   System.out.println("END");
                    }
                }

                UUID uid=UUID.randomUUID();
              //  System.out.println("INIT5 +"+uid.toString()+"!");
                Image i = new Image(uid, a[0], a[1], userRep.findByName(a[2]).get(0), co, ta);
              //  System.out.println("INIT 2 +"+i.getId()+"!");
                ing.add(i);
            }
        }

        imageRep.save( ing);

        String[] com = stringy[3].split("\\n");
        List<Coment> co= new ArrayList<Coment>();

        for(int index = 0; index < com.length; index++) {
            if (com[index].length()==4) {
                System.out.println("INIT5 +"+com[index].length()  + "x");
                String[] a = com[index].split(";");

                //Set<MyTag> ta = new HashSet<MyTag>();
                System.out.println("INIT 2 +" + a[0] + "!");
                Coment c = new Coment(UUID.randomUUID(), a[1], userRep.findByName(a[2]).get(0),
                        imageRep.findOne(UUID.fromString(a[0])));
                co.add(c);
            }
        }
        comentRep.save( co);



        log.debug("Finished");
        System.out.println("INIT 2 SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
    }
    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}