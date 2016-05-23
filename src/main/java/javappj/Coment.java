package javappj;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


/**
 * Created by The CAT on 23.5.2016.
 */
@Entity
@Table(name = "coments")
public class Coment {
    @Id
    @Column(name = "id_comm")
    private int id_comm;
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

    public int getId_comm() {
        return id_comm;
    }

    public void setId_comm(int id_comm) {
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
