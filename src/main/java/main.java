import datasource.MySQLConnector;
import datasource.MySQLPlaylistDAO;
import datasource.MySQLTrackDAO;
import datasource.MySQLUserDAO;
import datasource.dto.PlaylistDTO;
import datasource.dto.TrackDTO;
import domain.Playlist;
import domain.Track;
import service.LoginService;
import service.PlaylistService;

import java.util.ArrayList;
import java.util.List;

/**
 * Om snel iets te proberen zonder tests te hoeven schrijven :o
 */
public class main {
    public static void main (String [] args){
        PlaylistService playlistService = new PlaylistService();
        Playlist playlist = playlistService.getPlaylist(3);
        playlist.fillTracks();
        List<Track> tracks = playlist.getAddableTracks();
        System.out.println(tracks.size());
    }
}
