package projectForum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import projectForum.model.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by frodrok on 2015-06-21.
 */

@Repository
public class TopicRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    public List<Topic> findTopics() {
        return this.jdbcTemplate.query("select id, title, firstMessage, date, user_id from topics order by updated desc", new TopicRowMapper());
    }

    public void insert(Topic topic) {
        this.jdbcTemplate.update("insert into topics values (?, ?, ?, ?, ?, ?)", 0, topic.getTitle(), topic.getFirstMessage(), topic.getCreatedDate(), 1, topic.getUpdatedDate());
    }

    public void update(Topic topic) {
        this.jdbcTemplate.update("update topics set updated = " + topic.getUpdatedDate() + " where id = " + topic.getId());
    }

    public Topic findById(int id) {

        String sql = "select id, title, firstMessage, date, user_id from topics where id = ?";
        Topic topic = (Topic) jdbcTemplate.queryForObject(sql, new Object[] {id}, new TopicRowMapper());

        return topic;
    }

    // nested class otherwise it cannot reach userrepository
    class TopicRowMapper implements RowMapper<Topic> {

        @Override
        public Topic mapRow(ResultSet resultSet, int i) throws SQLException {

            return new Topic(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("firstMessage"),
                    resultSet.getLong("date"),
                    userRepository.findById(resultSet.getInt("user_id"))
            );

        }
    }

}