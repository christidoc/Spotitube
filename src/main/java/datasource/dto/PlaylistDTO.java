package datasource.dto;

/**
 * Created by Christiaan on 17-11-2017.
 */
public class PlaylistDTO {
    private int id;
    private String name;
    private String owner;

    public PlaylistDTO(){}

    public PlaylistDTO(int id, String name, String owner){
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
