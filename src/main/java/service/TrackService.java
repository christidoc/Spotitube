package service;

import datasource.TrackDAO;
import domain.Playlist;
import domain.Track;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christiaan on 16-1-2018.
 */
public class TrackService {
    @Inject
    TrackDAO trackDAO;

    public void addTrack(int playlistID, int trackID, boolean offlineAvailable) {
        trackDAO.addTrackToPlaylist(playlistID, trackID, offlineAvailable);
    }

    public List<Track> getTracksByPlaylist(int playlistID){
        return trackDAO.getAllTracksByPlaylist(playlistID);
    }

    public void fillTracksByPlaylist(Playlist playlist){
        playlist.getTracks().clear();
        List<Track> tracks = trackDAO.getAllTracksByPlaylist(playlist.getId());
        for(Track track : tracks){
            playlist.addTrack(track);
        }
    }

    public void deleteTrackByPlaylist(int playlistID, int trackID){
        trackDAO.removeTrackFromPlaylist(playlistID, trackID);
    }

    public List<Track> getAddableTracksByPlaylist(Playlist playlist){
        List<Track> addableTracks = new ArrayList<>();
        List<Track> tracks = trackDAO.getAllTracks();
        for (Track track : tracks) {
            boolean exist = false;
            for (Track playlistTrack : playlist.getTracks()) {
                if (playlistTrack.getId() == track.getId())
                    exist = true;
            }
            if (!exist) {
                addableTracks.add(track);
            }
        }
        return addableTracks;
    }
}
