package projectForum.model;

import javax.persistence.*;

/**
 * Created by User on 2015-06-15.
 */

@Entity
public class Topic {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "forumId")
    private Forum forum;

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
}
