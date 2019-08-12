package retake.instagraph.domain.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private Integer id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
