package projectForum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectForum.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
