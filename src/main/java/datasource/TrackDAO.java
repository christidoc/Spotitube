package datasource;

import datasource.dto.TrackDTO;

import java.util.List;

/**
 * Created by Christiaan on 26-11-2017.
 */
public interface TrackDAO {
    List<TrackDTO> getAllTracks();

    List<TrackDTO> getAllTracksByPlaylist(int playlistID);

    void removeTrackFromPlaylist(int playlistID, int trackID);

    void addTrackToPlaylist(int playlistID, TrackDTO track);

    void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable);
}
