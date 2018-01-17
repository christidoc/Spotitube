package domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private User owner;
    private List<Track> tracks;

    public Playlist() {
        tracks = new ArrayList<>();
    }

    public Playlist(int id, String name, User owner, List<Track> tracks) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public Track getTrack(int id) {
        for (Track track : tracks) {
            if (track.getId() == id) {
                return track;
            }
        }
        return null;
    }

    public List<Track> getTracks(){
        return tracks;
    }

    public int getLength() {
        int length = 0;
        for (Track track : tracks) {
            length += track.getDuration();
        }
        return length;
    }

    public void deleteTrack(int trackID){
        Track removeTrack = null;
        for(Track track : tracks){
            if(track.getId() == trackID){
                removeTrack = track;
            }
        }
        tracks.remove(removeTrack);
    }
}
