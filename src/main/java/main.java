import domain.Playlist;
import domain.Track;
import service.PlaylistService;
import service.TrackService;

import java.util.List;

/**
 * Om snel iets te proberen zonder tests te hoeven schrijven :o
 */
public class main {
    public static void main (String [] args){
        PlaylistService playlistService = new PlaylistService();
        TrackService trackService = new TrackService();
        Playlist playlist = playlistService.getPlaylist(3);
        trackService.fillTracksByPlaylist(playlist);
        List<Track> tracks = trackService.getAddableTracksByPlaylist(playlist);
        System.out.println(tracks.size());
    }
}
