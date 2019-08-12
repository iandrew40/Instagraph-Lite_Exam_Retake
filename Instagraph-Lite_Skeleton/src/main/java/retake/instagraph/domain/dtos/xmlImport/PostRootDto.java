package retake.instagraph.domain.dtos.xmlImport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostRootDto {

    @XmlElement(name = "post")
    private List<PostDto> postDtos;

    public PostRootDto() {
    }

    public List<PostDto> getPostDtos() {
        return this.postDtos;
    }

    public void setPostDtos(List<PostDto> postDtos) {
        this.postDtos = postDtos;
    }
}
