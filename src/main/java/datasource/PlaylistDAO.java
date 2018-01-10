package datasource;

import domain.Playlist;

import java.util.List;

/**
 * Created by Christiaan on 17-11-2017.
 */
public interface PlaylistDAO {
    List<Playlist> getAllPlaylists();

    void addPlaylist(Playlist playlist);

    void deletePlaylist(int playlistID);

    void updatePlaylist(Playlist playlist);

    Playlist getPlaylist(int playlistID);
}
