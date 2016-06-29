package javappj.data;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

/**
 * Created by The CAT
 * */


@Entity
@Table(name = "users")
@Document(collection = "users")
public class User {
    @Id
    @org.springframework.data.annotation.Id
    @Column(columnDefinition = "BINARY(16)")
   // @Type(type="text")
    private UUID id;

    @TextIndexed
    @Column(name = "name", unique=true)
    private String name;

    @Column(name = "registration")
    private Date registrationDate;

    public User() {
    }

    public User(UUID id) {
        this.id = id;
    }

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.registrationDate = new Date();
    }

    public User(UUID id, String name, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return !(registrationDate != null ? !registrationDate.equals(user.registrationDate) : user.registrationDate != null);

    }
}
