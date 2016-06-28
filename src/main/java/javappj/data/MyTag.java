package javappj.data;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * Created by The CAT
 * */
@Entity
@Table(name = "tags")
@Document(collection = "tags")
public class MyTag {


    @Id
    @org.springframework.data.annotation.Id
    @Column
    private UUID id;

    @Column(name = "value", unique=true)
    @Size(max = 16)
    private String value;

    @ManyToMany(mappedBy = "tagSet")
    @DBRef(lazy=true)
    private Set<Image> imageSet = new HashSet<>();

    public MyTag() {
    }

    public MyTag(UUID id) {
        this.id = id;
    }

    public MyTag(String value) {
        this.value = value;
    }

    public MyTag(UUID id, String value) {
        this.id = id;
        this.value = value;
    }

    public MyTag(UUID id, String value, Set<Image> imageSet) {
        this.id = id;
        this.value = value;
        this.imageSet = imageSet;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyTag tag = (MyTag) o;

        if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
        if (value != null ? !value.equals(tag.value) : tag.value != null) return false;
        return true;

    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
