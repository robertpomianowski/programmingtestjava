package pl.rpomiano.model.response;

/**
 * Created by aaroon on 25/01/2017.
 */
public class BookResponse {

    private String title;

    public BookResponse(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
