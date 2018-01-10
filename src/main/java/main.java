import domain.Playlist;
import domain.Track;
import service.PlaylistService;

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
