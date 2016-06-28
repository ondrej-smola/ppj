package javappj.data;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by The CAT on 23.5.2016.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Coment> coments;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Set<Coment> getComents() {
        return coments;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
