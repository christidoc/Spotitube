package presentation.dto;

import domain.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christiaan on 23-10-2017.
 */
public class PlaylistsResponse {
    private List<Playlist> playlists;
    private int length;

    public PlaylistsResponse(List<Playlist> playlists, int length){
        this.playlists = playlists;
        this.length = length;
    }

    public PlaylistsResponse(){
        playlists = new ArrayList<>();
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
