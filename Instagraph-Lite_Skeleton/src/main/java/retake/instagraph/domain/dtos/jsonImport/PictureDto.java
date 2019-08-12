package retake.instagraph.domain.dtos.jsonImport;

import com.google.gson.annotations.Expose;

public class PictureDto {

    @Expose
    private String path;

    @Expose
    private Double size;

    public PictureDto() {
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return this.size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
