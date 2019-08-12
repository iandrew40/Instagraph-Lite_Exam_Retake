package retake.instagraph.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.domain.dtos.jsonImport.UserDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.domain.entities.User;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.repository.UserRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String JSON_USERS_FILE_PATH = "/Users/andreyivanov/Documents/SoftUni/Java/Java DB/" +
            "Instagraph-Lite/Instagraph-Lite_Skeleton/src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public String readUsersFromJson() throws IOException {
        return this.fileUtil.readFile(JSON_USERS_FILE_PATH);
    }

    @Override
    public Boolean usersAreImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String importUsers(String users) {
        StringBuilder sb = new StringBuilder();

        UserDto[] dtos = this.gson.fromJson(users, UserDto[].class);

        for (UserDto dto : dtos) {
            User user = this.modelMapper.map(dto, User.class);

            Picture picture = this.pictureRepository.findByPath(dto.getProfilePicture());

            user.setProfilePicture(picture);

            if (!this.validationUtil.isValid(user)){
                sb.append("Error: Invalid data.\n");
                continue;
            }

            this.userRepository.saveAndFlush(user);
            sb.append(String.format("User %s successfully imported.\n", user.getUsername()));

        }

        return sb.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {
    StringBuilder sb = new StringBuilder();
    int number = 1;

//        List<User> users = this.userRepository.findByUser();
//
//        for (User user : users) {
//            sb.append(String.format("%d. %s - %d posts\n", number, user.getUsername()));
//                    number++;
//        }




    return sb.toString();
    }
}
