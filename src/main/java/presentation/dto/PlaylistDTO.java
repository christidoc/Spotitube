package presentation.dto;

import domain.Playlist;
import domain.Track;
import domain.User;
import service.ActiveUser;

import java.util.List;

/**
 * Created by Christiaan on 10-1-2018.
 */
public class PlaylistDTO {
    private int id;
    private String name;
    private Boolean owner;
    private List<Track> tracks;

    public PlaylistDTO (Playlist playlist, ActiveUser user){
        this.id = playlist.getId();
        this.name = playlist.getName();
        if(playlist.getOwner().equals(user.getUserName())){
            owner = true;
        }else{
            owner = false;
        }
        this.tracks = playlist.getTracks();
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

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
