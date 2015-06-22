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

    public List<Topic> findTopics(String sortBy) {

        String sql = "select id, title, first_message, created_date, user_id, ratings_amount/ratings_total as 'rating' from topics ";
        String orderBy = "";

        if (sortBy.equals("newest")) {
            orderBy = "order by created_date desc";
        } else if (sortBy.equals("oldest")) {
            orderBy = "order by created_date asc";
        } else if (sortBy.equals("highest")) {
            sql = "select id, title, first_message, created_date, user_id, ratings_amount/ratings_total as 'rating' from topics ";
            orderBy = "order by rating desc";
        } else if (sortBy.equals("lowest")) {
            sql = "select id, title, first_message, created_date, user_id, ratings_amount/ratings_total as 'rating' from topics ";
            orderBy = "order by rating asc";
        }

        return this.jdbcTemplate.query(sql + orderBy, new TopicRowMapper());
        // return this.jdbcTemplate.query("select id, title, first_message, created_date, user_id from topics order by updated_date desc", new TopicRowMapper());
    }

    public void insert(Topic topic) {
        this.jdbcTemplate.update("insert into topics values (?, ?, ?, ?, ?, ?, ?, ?)",
                0, topic.getTitle(), topic.getFirstMessage(), 1, topic.getCreatedDate(), topic.getUpdatedDate(), 0, 0);
    }

    public void update(Topic topic) {
        this.jdbcTemplate.update("update topics set updated_date = " + topic.getUpdatedDate() + " where id = " + topic.getId());
    }

    public Topic findById(int id) {

        String sql = "select id, title, first_message, created_date, user_id, ratings_amount/ratings_total as 'rating' from topics where id = ?";
        Topic topic = (Topic) jdbcTemplate.queryForObject(sql, new Object[] {id}, new TopicRowMapper());

        return topic;
    }

    // nested class otherwise it cannot reach userrepository
    class TopicRowMapper implements RowMapper<Topic> {

        @Override
        public Topic mapRow(ResultSet resultSet, int i) throws SQLException {

            return new Topic(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("first_message"),
                    resultSet.getLong("created_date"),
                    resultSet.getInt("rating"),
                    userRepository.findById(resultSet.getInt("user_id"))
            );

        }
    }

}