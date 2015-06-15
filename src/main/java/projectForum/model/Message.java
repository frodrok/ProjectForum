package projectForum.model;

import javax.persistence.*;

/**
 * Created by User on 2015-06-15.
 */

@Entity
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "topicId")
    private Topic topic;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private String messageContent;

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
