package domain;

import datasource.MySQLTrackDAO;
import datasource.TrackDAO;
import datasource.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private String owner;
    private List<Track> tracks;
    private TrackDAO mySQLTrackDAO;

    public Playlist() {
        tracks = new ArrayList<>();
        mySQLTrackDAO = new MySQLTrackDAO();
    }

    public Playlist(int id, String name, String owner, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
        mySQLTrackDAO = new MySQLTrackDAO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void addTrack(int trackID, boolean offlineAvailable) {
        mySQLTrackDAO.addTrackToPlaylist(id, trackID, offlineAvailable);
        fillTracks();
    }

    public Track getTrack(int id) {
        for (Track track : tracks) {
            if (track.getId() == id) {
                return track;
            }
        }
        return null;
    }

    public List<Track> getTracks(){
        return tracks;
    }

    public int getLength() {
        int length = 0;
        for (Track track : tracks) {
            length += track.getDuration();
        }
        return length;
    }

    public void fillTracks() {
        List<TrackDTO> DTOtracks = mySQLTrackDAO.getAllTracksByPlaylist(id);

        for (TrackDTO trackDTO : DTOtracks) {
            Track track = new Track();
            track.setId(trackDTO.getId());
            track.setTitle(trackDTO.getTitle());
            track.setPerformer(trackDTO.getPerformer());
            track.setDuration(trackDTO.getDuration());
            track.setPlaycount(trackDTO.getPlaycount());
            track.setOfflineAvailable(trackDTO.isOfflineAvailable());
            //track.setOfflineAvailable(true);
            tracks.add(track);
        }
    }

    public List<Track> getAddableTracks() {
        List<Track> tracks = new ArrayList<>();
        List<TrackDTO> DTOtracks = mySQLTrackDAO.getAllTracks();
        for (TrackDTO trackDTO : DTOtracks) {
            boolean exist = false;
            for (Track track : this.tracks) {
                if (track.getId() == trackDTO.getId())
                    exist = true;
            }
            if (!exist) {
                Track track = new Track();
                track.setId(trackDTO.getId());
                track.setTitle(trackDTO.getTitle());
                track.setPerformer(trackDTO.getPerformer());
                track.setDuration(trackDTO.getDuration());
                track.setPlaycount(trackDTO.getPlaycount());
                //Fout in geleverde documentatie waardoor niet duidelijk is of offlineavailable bij Track hoort of bij Track in Playlist.
                track.setOfflineAvailable(true);
                tracks.add(track);
            }
        }
        return tracks;
    }

    public void deleteTrack(int trackID){
        mySQLTrackDAO.removeTrackFromPlaylist(this.id, trackID);
        Track removeTrack = null;
        for(Track track : tracks){
            if(track.getId() == trackID){
                removeTrack = track;
            }
        }
        tracks.remove(removeTrack);
    }
}
