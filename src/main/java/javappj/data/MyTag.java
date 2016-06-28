package javappj.data;

import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by The CAT on 23.5.2016.
 */

@Entity
@Table(name = "tags")
public class MyTag {

    @ManyToMany(mappedBy = "tagSet")
    @DBRef(lazy=true)
    private Set<Image> imageSet = new HashSet<>();
    @Id
    @Column(name = "tag")
    private String tag;

    public MyTag(String tag) {
        this.tag = tag;
    }
}
