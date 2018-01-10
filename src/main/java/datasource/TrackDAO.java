package datasource;

import domain.Track;

import java.util.List;

/**
 * Created by Christiaan on 26-11-2017.
 */
public interface TrackDAO {
    List<Track> getAllTracks();

    List<Track> getAllTracksByPlaylist(int playlistID);

    void removeTrackFromPlaylist(int playlistID, int trackID);

    void addTrackToPlaylist(int playlistID, Track track);

    void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable);
}
