package javappj.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by The CAT
 */
@Entity
@Table(name = "comments")
@Document(collection = "comments")
public class Coment {
    @Id
    @org.springframework.data.annotation.Id
    @Column
    private UUID id;

    @Column(name = "comment")
    private String comment;

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
    @DBRef(lazy=true)
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "name_id")
    @DBRef(lazy=true)
    private Image image;

    public Coment() {
    }

    public Coment(UUID id) {
        this.id = id;
    }

    public Coment(UUID id, String comment, User user, Image image) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.createdDate = new Date();
        this.likesCount = 0;
        this.dislikesCount = 0;
        this.image = image;
    }

    public Coment(UUID id, String comment, User user) {
        this.id = id;
        this.comment = comment;
        this.user = user;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }



    public void setDislikesCount(Integer dislikesCount) {
        this.dislikesCount = dislikesCount;
    }
    public void Like() {
        this.likesCount = this.likesCount+1;
    }

    public Integer getDislikesCount() {
        return dislikesCount;
    }

    public void Dislike() {
        this.dislikesCount =  this.dislikesCount+1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User author) {
        this.user = author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coment comment1 = (Coment) o;

        if (id != null ? !id.equals(comment1.id) : comment1.id != null) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (createdDate != null ? !createdDate.equals(comment1.createdDate) : comment1.createdDate != null)
            return false;
        if (changedDate != null ? !changedDate.equals(comment1.changedDate) : comment1.changedDate != null)
            return false;
        if (likesCount != null ? !likesCount.equals(comment1.likesCount) : comment1.likesCount != null) return false;
        if (dislikesCount != null ? !dislikesCount.equals(comment1.dislikesCount) : comment1.dislikesCount != null)
            return false;
        if (user != null ? !user.equals(comment1.user) : comment1.user != null) return false;
        if (image != null ? !image.equals(comment1.image) : comment1.image != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                ", changedDate=" + changedDate +
                ", likesCount=" + likesCount +
                ", dislikesCount=" + dislikesCount +
                ", user=" + user +
                ", image=" + image.getName() +
                '}';
    }
}
