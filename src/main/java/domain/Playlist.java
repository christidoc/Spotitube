package domain;

import datasource.PlaylistDAO;
import datasource.PlaylistMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Playlist extends DomainObject{
    public static PlaylistMapper playlistMapper = new PlaylistMapper();
    private int id;
    private String name;
    private User owner;
    private List<Track> tracks;
    private int length;

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
        calculateLength();
    }

    public int getLength(){return length; }

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

    public void deleteTrack(int trackID){
        Track removeTrack = null;
        for(Track track : tracks){
            if(track.getId() == trackID){
                removeTrack = track;
            }
        }
        tracks.remove(removeTrack);
        calculateLength();
    }

    //Logic
    public void calculateLength() {
        length = 0;
        for (Track track : tracks) {
            length += track.getDuration();
        }
    }

    public void deletePlaylist(){

    }

    public void insert(){
        playlistMapper.insert(this);
    }

    public static List<Playlist> getAllPlaylists(){
        return playlistMapper.getAllPlaylists();
    }

    public static Playlist getPlaylist(int id) {
        return playlistMapper.getPlaylist(id);
    }

    public void update (){
        playlistMapper.update(this);
    }
}
