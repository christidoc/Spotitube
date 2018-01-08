package presentation.dto;

import domain.Track;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsRequest {
    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks;

    public PlaylistsRequest(){}

    public PlaylistsRequest(int id, String name, boolean owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = new ArrayList<>();
    }

    public PlaylistsRequest(int id, String name, boolean owner, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
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

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
