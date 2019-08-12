package retake.instagraph.domain.dtos.jsonImport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class UserDto {

    @Expose
    private String username;

    @Expose
    private String password;

    @NotNull
    @Expose
    @SerializedName("profile_picture")
    private String profilePicture;

    public UserDto() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
