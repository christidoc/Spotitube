package presentation.dto;

import domain.Track;

import java.util.List;

/**
 * Created by Christiaan on 15-11-2017.
 */
public class TrackResponse {
    private List<Track> tracks;

    public TrackResponse(){}

    public TrackResponse(List<Track> tracks){
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
