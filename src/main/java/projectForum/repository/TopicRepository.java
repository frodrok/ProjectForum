package projectForum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectForum.model.Message;
import projectForum.model.Topic;
import projectForum.model.User;

import java.util.List;

/**
 * Created by User on 2015-06-15.
 */

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    List<Topic> findAll();
}


