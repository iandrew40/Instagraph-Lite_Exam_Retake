package retake.instagraph.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retake.instagraph.domain.entities.Picture;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Picture findByPath(String path);

    @Query(value = "SELECT p FROM Picture p WHERE p.size > 30000 ORDER BY p.size asc")
    List<Picture> findPictureWithSize();
}
