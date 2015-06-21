package projectForum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import projectForum.model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by frodrok on 2015-06-21.
 */

@Repository
public class MessageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    public void insert(Message message) {
        this.jdbcTemplate.update("insert into messages values (?, ?, ?, ?, ?)",
                0, message.getMessage(), message.getCreated_date(), message.getTopic_id(), message.getUser().getId());

    }

    public List<Message> findAllById(int id) {
        String sql = "select message, date, topic_id, user_id from messages order by date asc";
        return this.jdbcTemplate.query(sql, new MessageRowMapper());
    }

    class MessageRowMapper implements RowMapper<Message> {
        @Override
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Message(resultSet.getString("message"),
                    resultSet.getLong("date"),
                    resultSet.getInt("topic_id"),
                    userRepository.findById(resultSet.getInt("user_id"))
                    );
        }
    }
}
