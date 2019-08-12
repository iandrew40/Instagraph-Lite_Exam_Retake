package retake.instagraph.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.domain.dtos.xmlImport.PostDto;
import retake.instagraph.domain.dtos.xmlImport.PostRootDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.domain.entities.Post;
import retake.instagraph.domain.entities.User;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.repository.PostRepository;
import retake.instagraph.repository.UserRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    private static final String XML_POSTS_FILE_PATH = "/Users/andreyivanov/Documents/SoftUni/Java/Java DB/" +
            "Instagraph-Lite/Instagraph-Lite_Skeleton/src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String readPostFromXmlFile() throws IOException {
        return this.fileUtil.readFile(XML_POSTS_FILE_PATH);
    }

    @Override
    public String importPosts(String posts) throws JAXBException {
        StringBuilder sb = new StringBuilder();

        JAXBContext context = JAXBContext.newInstance(PostRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        PostRootDto dtos = (PostRootDto) unmarshaller.unmarshal(new File(XML_POSTS_FILE_PATH));

        for (PostDto postDto : dtos.getPostDtos()) {
            Post post = this.modelMapper.map(postDto, Post.class);

            User user = this.userRepository.findByUsername(postDto.getUser());
            if (user == null) {
                sb.append("Error: Invalid data.\n");
                continue;
            }
            post.setUser(user);

            Picture picture = this.pictureRepository.findByPath(postDto.getPicture());
            if (picture == null) {
                sb.append("Error: Invalid data.\n");
                continue;
            }
            post.setPicture(picture);


            if (!this.validationUtil.isValid(post)) {
                sb.append("Error: Invalid data.\n");
                continue;
            }

            this.postRepository.saveAndFlush(post);
            sb.append(String.format("Post %s successfully imported.\n", post.getCaption()));

        }


        return sb.toString();
    }

    @Override
    public Boolean postsAreImported() {
        return this.postRepository.count() > 0;
    }
}
