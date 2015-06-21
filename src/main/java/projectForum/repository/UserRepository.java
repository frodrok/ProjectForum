package projectForum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import projectForum.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by frodrok on 2015-06-21.
 */

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findById(int id) {

        String sql = "select * from users where id = ?";
        User user = (User) jdbcTemplate.queryForObject(sql, new Object[] {id}, new UserRowMapper());

        /* nödlösning List<User> users = this.jdbcTemplate.query("select id, username, password from users where id = " + id, new UserRowMapper());
        return users.get(0); */

        return user;
    }
}

class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
    }
}