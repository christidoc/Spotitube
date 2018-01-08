package datasource;

import datasource.dto.PlaylistDTO;

import java.util.List;

/**
 * Created by Christiaan on 17-11-2017.
 */
public interface PlaylistDAO {
    List<PlaylistDTO> getAllPlaylists();

    void addPlaylist(PlaylistDTO playlist);

    void deletePlaylist(int playlistID);

    void updatePlaylist(PlaylistDTO playlist);

    PlaylistDTO getPlaylist(int playlistID);
}
