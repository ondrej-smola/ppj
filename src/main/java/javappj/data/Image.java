package javappj.data;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by The CAT on 23.5.2016.
 */

@Entity
@Table(name = "coments")
public class Image {
    @Id
    @Column(name = "img_id")
    private UUID img_id;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Coment> coments = new HashSet<>();
    @Column(name = "name")
    private String name;
    @Column(name = "loc")
    private String location;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id")
    @DBRef
    private User user;
    @Column(name = "like")
    private int likes;
    @Column(name = "dislike")
    private int dislike;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "image_tags",
            joinColumns = {@JoinColumn(name = "img_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"img_id", "tag"})})
    @DBRef
    private Set<MyTag> tagSet = new HashSet<>();
    public UUID getImg_id() {
        return img_id;
    }

    public void setImg_id(UUID img_id) {
        this.img_id = img_id;
    }

    public Set<Coment> getComents() {
        return coments;
    }

    public void setComents(Set<Coment> coments) {
        this.coments = coments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLikes() {
        return likes;
    }

    public Image(UUID img_id,String name,  String location,User user,Set<Coment> coments,Set<MyTag> tagSet  ) {
        this.img_id = img_id;
        this.name=name;
        this.user=user;

        this.coments=coments;
        this.tagSet= tagSet;
        this.location = location;
        this.likes = 0;
        this.dislike = 0;
    }

    public void Like() {
        this.likes = this.likes+1;
    }



    public int getDislike() {
        return dislike;
    }

    public void Dislike() {
        this.dislike = this.dislike+1;
    }
}
