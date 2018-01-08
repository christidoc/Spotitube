package service;

import datasource.MySQLPlaylistDAO;
import datasource.PlaylistDAO;
import datasource.dto.PlaylistDTO;
import domain.Playlist;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christiaan on 17-11-2017.
 */
public class PlaylistService {
    @Inject
    PlaylistDAO playlistDAO;

    public List<Playlist> getPlaylists(){
        List<PlaylistDTO> DTOplaylists = playlistDAO.getAllPlaylists();
        List<Playlist> playlists = new ArrayList<>();

        for(PlaylistDTO playlistDTO : DTOplaylists){
            Playlist playlist = new Playlist();
            playlist.setId(playlistDTO.getId());
            playlist.setName(playlistDTO.getName());
            playlist.setOwner(playlistDTO.getOwner());
            playlists.add(playlist);
        }
        return playlists;
    }

    public Playlist getPlaylist(int playlistID){
        PlaylistDTO playlistDTO = playlistDAO.getPlaylist(playlistID);
        Playlist playlist = new Playlist();
        playlist.setId(playlistDTO.getId());
        playlist.setName(playlistDTO.getName());
        playlist.setOwner(playlistDTO.getOwner());
        return playlist;
    }

    public void deletePlaylist(int playlistID){
        playlistDAO.deletePlaylist(playlistID);
    }

    public void addPlaylist(Playlist playlist){
        playlistDAO.addPlaylist(new PlaylistDTO(-1, playlist.getName(), playlist.getOwner()));
    }

    public void editPlaylist(Playlist playlist){
        playlistDAO.updatePlaylist(new PlaylistDTO(playlist.getId(), playlist.getName(), playlist.getOwner()));
    }
}
