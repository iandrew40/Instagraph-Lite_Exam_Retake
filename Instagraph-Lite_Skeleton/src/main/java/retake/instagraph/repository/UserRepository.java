package retake.instagraph.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retake.instagraph.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

  //  @Query(value = "Select u.username, COUNT(p.id) FROM User u JOIN Post p on u.id = p.id group by u.id")
  //  List<User> findByUser();


}
