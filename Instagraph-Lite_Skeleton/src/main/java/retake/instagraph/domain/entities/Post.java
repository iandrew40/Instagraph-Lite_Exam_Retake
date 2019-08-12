package retake.instagraph.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    private String caption;
    private User user;
    private Picture picture;

    public Post() {
    }

    @NotNull
    @Column(name = "caption", nullable = false)
    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    @OneToOne(targetEntity = Picture.class)
    @JoinColumn(name = "picture_id", referencedColumnName = "id", nullable = false)
    public Picture getPicture() {
        return this.picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
