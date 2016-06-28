package javappj.data;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by The CAT
 * */
@Entity
@Table(name = "images")
@Document(collection = "images")
public class Image {

    @Id
    @org.springframework.data.annotation.Id
    @Column
    private UUID id;

    @Column(name = "name", unique=true)
    private String name;

    @Column(name = "location")
    private String Location;

    @Column(name = "created")
    private Date createdDate;

    @Column(name = "changed")
    private Date changedDate;

    @Column(name = "likes")
    private Integer likesCount;

    @Column(name = "dislikes")
    private Integer dislikesCount;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id")
    @DBRef
    private User user;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @DBRef
    private Set<Coment> commentSet = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "image_tags",
            joinColumns = {@JoinColumn(name = "image_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"image_id", "tag_id"})})
    @DBRef
    private Set<MyTag> tagSet = new HashSet<>();

    public Image() {
    }

    public Image(UUID id) {
        this.id = id;
    }

    public Image(String name) {
        this.name = name;
    }

    public Image(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Image(UUID id, String name, String Location, User user, Set<Coment> commentSet, Set<MyTag> tagSet ) {
        this.id = id;
        this.name = name;
        this.Location = Location;
        this.user = user;
        this.commentSet = commentSet;
        this.tagSet = tagSet;
        this.likesCount = 0;
        this.dislikesCount = 0;
        this.createdDate = new Date();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void Like() {
        this.likesCount = this.likesCount+1;
    }

    public Integer getDislikesCount() {
        return dislikesCount;
    }

    public void Dislike() {
        this.dislikesCount = this.dislikesCount+1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Coment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Coment> commentSet) {
        this.commentSet = commentSet;
    }

    public Set<MyTag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<MyTag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + Location + '\'' +
                ", createdDate=" + createdDate +
                ", changedDate=" + changedDate +
                ", likesCount=" + likesCount +
                ", dislikesCount=" + dislikesCount +
                ", user=" + user +
                ", commentSet=" + commentSet +
                ", tagSet=" + tagSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (id != null ? !id.equals(image.id) : image.id != null) return false;
        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        if (Location != null ? !Location.equals(image.Location) : image.Location != null) return false;
        if (createdDate != null ? !createdDate.equals(image.createdDate) : image.createdDate != null) return false;
        if (changedDate != null ? !changedDate.equals(image.changedDate) : image.changedDate != null) return false;
        if (likesCount != null ? !likesCount.equals(image.likesCount) : image.likesCount != null) return false;
        if (dislikesCount != null ? !dislikesCount.equals(image.dislikesCount) : image.dislikesCount != null)
            return false;
        if (user != null ? !user.getId().equals(image.user.getId()) : image.user != null) return false;
        return true;
    }
}
