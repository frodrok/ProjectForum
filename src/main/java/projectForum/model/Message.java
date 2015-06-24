package projectForum.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * Created by frodrok on 2015-06-21.
 */

public class Message {

    @NotEmpty
    @Length(min = 10, max = 500)
    private String message;


    private User user;
    private Long created_date;
    private int topic_id;

    public Message() {
    }

    public Message(String message, Long created_date, int topic_id, User user) {
        this.message = message;
        this.user = user;
        this.created_date = created_date;
        this.topic_id = topic_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Long created_date) {
        this.created_date = created_date;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public Date getCreatedDateAsDateObject() {
        return new Date(this.created_date);
    }
}
