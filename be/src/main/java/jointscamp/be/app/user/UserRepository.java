package jointscamp.be.app.user;

import jointscamp.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM users u left join fetch u.produks WHERE u.email = :email")
    public Optional<User> getUserByEmail(@Param("email") String email);
}
