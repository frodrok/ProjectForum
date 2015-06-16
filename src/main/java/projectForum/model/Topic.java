package projectForum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 2015-06-15.
 */

@Entity
@Table(name = "topics")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="topic_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @OneToMany(mappedBy="topic")
    @OrderBy("id ASC")
    private Set<Message> messages;

    @Column(name = "topic_date")
    private Long date;

    private String title;

    public Topic() {}

    public Topic(Forum forum, String title) {
        this.forum = forum;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
