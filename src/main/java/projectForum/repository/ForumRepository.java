package projectForum.repository;

import org.springframework.data.repository.CrudRepository;
import projectForum.model.Forum;

/**
 * Created by User on 2015-06-16.
 */
public interface ForumRepository extends CrudRepository<Forum, Long> {
}
