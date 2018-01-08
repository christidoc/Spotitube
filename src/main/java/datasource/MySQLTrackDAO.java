package datasource;

import datasource.dto.PlaylistDTO;
import datasource.dto.TrackDTO;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLTrackDAO implements  TrackDAO{
    //@Inject
    //DBConnector mySQLConnector;
    DBConnector mySQLConnector = new MySQLConnector();

    public List<TrackDTO> getAllTracks(){
        List<TrackDTO> tracks = new ArrayList<>();

        String query = ("SELECT * FROM track");
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getInt("publicationDate"));
                track.setDescription(resultSet.getString("description"));
                tracks.add(track);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return tracks;
    }

    public List<TrackDTO> getAllTracksByPlaylist(int playlistID){
        List<TrackDTO> tracks = new ArrayList<>();

        String query = ("select * from track JOIN playlisttrack ON track.id = playlisttrack.track WHERE playlist = " + playlistID);
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getInt("publicationDate"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offlineavailable"));
                tracks.add(track);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return tracks;
    }

    public void removeTrackFromPlaylist(int playlistID, int trackID){
        String query = ("DELETE FROM playlisttrack WHERE playlist = " + playlistID + " AND track = " + trackID);
        mySQLConnector.updateSomethingInDatabase(query);
    }

    public void addTrackToPlaylist(int playlistID, TrackDTO track){
        //Lookup track before add it to the playlist && check of offlineavailable(boolean) automatisch goed gaat in db.
        String query = ("INSERT INTO playlisttrack (playlist, track, offlineavailable) VALUES(" + playlistID + "," + track.getId() + "," + track.isOfflineAvailable() + ")");
        mySQLConnector.updateSomethingInDatabase(query);
    }

    public void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable){
        String query = ("INSERT INTO playlisttrack (playlist, track, offlineavailable) VALUES(" + playlistID + "," + trackID + "," + offlineAvailable + ")");
        mySQLConnector.updateSomethingInDatabase(query);
    }

}
