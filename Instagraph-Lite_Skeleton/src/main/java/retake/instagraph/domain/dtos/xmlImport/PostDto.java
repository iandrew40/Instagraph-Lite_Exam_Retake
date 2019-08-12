package retake.instagraph.domain.dtos.xmlImport;

import retake.instagraph.domain.entities.Picture;
import retake.instagraph.domain.entities.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostDto {

    @XmlElement(name = "caption")
    private String caption;

    @XmlElement(name = "user")
    private String user;

    @XmlElement(name = "picture")
    private String picture;

    public PostDto() {
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
