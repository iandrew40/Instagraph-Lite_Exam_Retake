package retake.instagraph.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private Picture profilePicture;
    private List<Post> posts;

    public User() {
    }

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @OneToOne(targetEntity = Picture.class)
    @JoinColumn(name = "profile_picture_id", referencedColumnName = "id", nullable = false)
    public Picture getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @OneToMany(mappedBy = "user")
    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}