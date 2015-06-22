package projectForum.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by User on 2015-06-22.
 */

public class Search {

    @NotEmpty
    private String searchString;

    public Search() {
    }

    public Search(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
