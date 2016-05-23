package javappj;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by The CAT on 23.5.2016.
 */

@Entity
@Table(name = "tags")
public class my_tag {

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="BLOG_POST_TAGS",
            joinColumns={@JoinColumn(name="tag")},
            inverseJoinColumns={@JoinColumn(name="Img_id")})
    private Set<Image> images ;
    @Id
    @Column(name = "tag")
    private String tag;

}
