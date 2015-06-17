package projectForum.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 2015-06-15.
 */

@Entity
@Table(name = "messages")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    private String messageContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {

        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Message() {}

    public Message(Topic topic, User user, String messageContent) {
        this.topic = topic;
        this.user = user;
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
