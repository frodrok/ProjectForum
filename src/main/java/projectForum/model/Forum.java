package projectForum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "forums")
public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="forum_id")
    private Long id;

    @OneToMany(mappedBy="forum")
    @OrderBy("topic_date DESC")
    private Set<Topic> topicList;

    public Forum() {}

    public Forum(Set<Topic> topicList) {
        this.topicList = topicList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(Set<Topic> topicList) {
        this.topicList = topicList;
    }
}
