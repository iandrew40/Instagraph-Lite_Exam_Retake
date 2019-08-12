package retake.instagraph.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.domain.dtos.jsonImport.PictureDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class PictureServiceImpl implements PictureService {

    private static final String JSON_PICTURE_FILE_PATH = "/Users/andreyivanov/Documents/SoftUni/Java/Java DB/" +
            "Instagraph-Lite/Instagraph-Lite_Skeleton/src/main/resources/files/pictures.json";

    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public Boolean picturesAreImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromJsonFile() throws IOException {
        return this.fileUtil.readFile(JSON_PICTURE_FILE_PATH);
    }

    @Override
    public String importPictures(String pictures) {
        StringBuilder sb = new StringBuilder();

        PictureDto[] dtos = this.gson.fromJson(pictures, PictureDto[].class);

        for (PictureDto dto : dtos) {
            Picture picture = this.modelMapper.map(dto, Picture.class);

            if (!this.validationUtil.isValid(picture)){
                sb.append("Error: Incorrect Data!\n");
                continue;
            }

            this.pictureRepository.saveAndFlush(picture);
            sb.append(String.format("Picture with path:%s and size:%f successfully imported.\n",
                    picture.getPath(), picture.getSize()));


        }

        return sb.toString();
    }

    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();

        List<Picture> pictures = this.pictureRepository.findPictureWithSize();

        for (Picture picture : pictures) {

            File f = new File(picture.getPath());


            sb.append(String.format("%s - %.2f\n", f.getName(), picture.getSize()));

        }

        return sb.toString();
    }
}
