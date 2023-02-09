package jointscamp.be.app.user;

import jointscamp.be.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE email = ?1")
    public Optional<User> getUserByEmail(String email);
}
