package domain;

/**
 * Created by Christiaan on 26-11-2017.
 */
public class Video extends Track{
    //    private Date publicationdate;
    private String description;

    public void as(){
        description.toCharArray();
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
