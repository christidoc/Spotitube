package presentation.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christiaan on 23-10-2017.
 */
public class PlaylistsResponse {
    private List<PlaylistDTO> playlists;
    private int length;

    public PlaylistsResponse(List<PlaylistDTO> playlists, int length){
        this.playlists = playlists;
        this.length = length;
    }

    public PlaylistsResponse(){
        playlists = new ArrayList<>();
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(PlaylistDTO playlist){
        playlists.add(playlist);
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
