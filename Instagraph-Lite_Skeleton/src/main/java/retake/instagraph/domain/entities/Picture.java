package retake.instagraph.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String path;
    private Double size;

    public Picture() {
    }

    @NotNull
    @Column(name = "path", nullable = false)
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @NotNull
    @Column(name = "size", nullable = false)
    public Double getSize() {
        return this.size;
    }

    public void setSize(Double size) {
        this.size = size;
    }



}
