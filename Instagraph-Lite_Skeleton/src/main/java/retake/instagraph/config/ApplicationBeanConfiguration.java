package retake.instagraph.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.FileUtilImpl;
import retake.instagraph.util.ValidationUtil;
import retake.instagraph.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Gson gson() {

       return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidationUtil validationUtil() {
         return new ValidationUtilImpl(validator());
    }

    @Bean
    public ModelMapper modelMapper() {
       return new ModelMapper();
    }
}
