package projectForum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import projectForum.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
