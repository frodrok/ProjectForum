package projectForum.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by User on 2015-06-22.
 */

public class Rating {

    @Min(1)
    @Max(5)
    private int rating;

    @Min(1)
    private int topic_id;

    public Rating() {
    }

    public Rating(int rating, int topic_id) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }
}
