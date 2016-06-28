package javappj.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;


/**
 * Created by The CAT on 23.5.2016.
 */
@Entity
@Table(name = "coments")
public class Coment {
    @Id
    @Column(name = "id_comm")
    private UUID id_comm;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "users_offers",
            joinColumns = {@JoinColumn(name = "username")},
            inverseJoinColumns = {@JoinColumn(name = "user")})
//    @JoinColumn(name = "username")
    private User user;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "users_offers",
            joinColumns = {@JoinColumn(name = "img_id")},
            inverseJoinColumns = {@JoinColumn(name = "img")})
//    @JoinColumn(name = "username")
    private Image img;
    @Column(name = "text")
    private String text;
    @Column(name = "like")
    private int likes;
    @Column(name = "dislike")
    private int dislike;


    public Coment(UUID id_comm, User user, Image img, String text) {
        this.id_comm = id_comm;
        this.user = user;
        this.img = img;
        this.text = text;
        this.likes=0;
        this.dislike=0;
    }
    public void Like() {
        this.likes = this.likes+1;
    }


    public void Dislike() {
        this.dislike = this.dislike+1;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislike() {
        return dislike;
    }
    public UUID getId_comm() {
        return id_comm;
    }

    public void setId_comm(UUID id_comm) {
        this.id_comm = id_comm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
