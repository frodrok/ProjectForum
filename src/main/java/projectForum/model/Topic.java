package projectForum.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by frodrok on 2015-06-21.
 */
public class Topic {

    private int id;

    @NotEmpty
    private String title;

   private User user;

    private Long createdDate;

    private Long updatedDate;

    @NotEmpty
    private String firstMessage;

    public Topic() {
    }

    public Topic(int id, String title, String firstMessage, Long createdDate, User user) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.user = user;
        this.firstMessage = firstMessage;
    }

    public Topic(int id, String title, String firstMessage) {
        this.id = id;
        this.title = title;
        this.firstMessage = firstMessage;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(String firstMessage) {
        this.firstMessage = firstMessage;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }
}
