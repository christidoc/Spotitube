package service;

import datasource.PlaylistDAO;
import domain.Playlist;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Christiaan on 17-11-2017.
 */
public class PlaylistService {
    @Inject
    PlaylistDAO playlistDAO;

    public List<Playlist> getPlaylists(){
        return playlistDAO.getAllPlaylists();
    }

    public Playlist getPlaylist(int playlistID){
        return playlistDAO.getPlaylist(playlistID);
    }

    public void deletePlaylist(int playlistID){
        playlistDAO.deletePlaylist(playlistID);
    }

    public void addPlaylist(Playlist playlist){
        playlistDAO.addPlaylist(playlist);
    }

    public void editPlaylist(Playlist playlist){
        playlistDAO.updatePlaylist(playlist);
    }
}
