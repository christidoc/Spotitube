package datasource;

import domain.Song;
import domain.Track;
import domain.Video;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLTrackDAO implements  TrackDAO{
    @Inject
    DBConnector mySQLConnector;
    //DBConnector mySQLConnector = new MySQLConnector();

    public List<Track> getAllTracks(){
        List<Track> tracks = new ArrayList<>();

        String query = ("SELECT * FROM track");
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while (resultSet.next()) {
                if(resultSet.getBoolean("song")){
                    Song song = new Song();
                    song.setId(resultSet.getInt("id"));
                    song.setTitle(resultSet.getString("title"));
                    song.setPerformer(resultSet.getString("performer"));
                    song.setDuration(resultSet.getInt("duration"));
                    song.setAlbum(resultSet.getString("album"));
                    song.setPlaycount(resultSet.getInt("playcount"));
                    tracks.add(song);
                }
                else{
                    Video video = new Video();
                    video.setId(resultSet.getInt("id"));
                    video.setTitle(resultSet.getString("title"));
                    video.setPerformer(resultSet.getString("performer"));
                    video.setDuration(resultSet.getInt("duration"));
                    video.setPlaycount(resultSet.getInt("playcount"));
                    video.setDescription(resultSet.getString("description"));
                    tracks.add(video);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return tracks;
    }

    public List<Track> getAllTracksByPlaylist(int playlistID){
        List<Track> tracks = new ArrayList<>();

        String query = ("select * from track JOIN playlisttrack ON track.id = playlisttrack.track WHERE playlist = " + playlistID);
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while (resultSet.next()) {
                if(resultSet.getBoolean("song")){
                    Song song = new Song();
                    song.setId(resultSet.getInt("id"));
                    song.setTitle(resultSet.getString("title"));
                    song.setPerformer(resultSet.getString("performer"));
                    song.setDuration(resultSet.getInt("duration"));
                    song.setAlbum(resultSet.getString("album"));
                    song.setPlaycount(resultSet.getInt("playcount"));
                    song.setOfflineAvailable(resultSet.getBoolean("offlineavailable"));
                    tracks.add(song);
                }
                else{
                    Video video = new Video();
                    video.setId(resultSet.getInt("id"));
                    video.setTitle(resultSet.getString("title"));
                    video.setPerformer(resultSet.getString("performer"));
                    video.setDuration(resultSet.getInt("duration"));
                    video.setPlaycount(resultSet.getInt("playcount"));
                    video.setDescription(resultSet.getString("description"));
                    video.setOfflineAvailable(resultSet.getBoolean("offlineavailable"));
                    tracks.add(video);
                }
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

    public void addTrackToPlaylist(int playlistID, Track track){
        //Lookup track before add it to the playlist && check of offlineavailable(boolean) automatisch goed gaat in db.
        String query = ("INSERT INTO playlisttrack (playlist, track, offlineavailable) VALUES(" + playlistID + "," + track.getId() + "," + track.isOfflineAvailable() + ")");
        mySQLConnector.updateSomethingInDatabase(query);
    }

    public void addTrackToPlaylist(int playlistID, int trackID, boolean offlineAvailable){
        String query = ("INSERT INTO playlisttrack (playlist, track, offlineavailable) VALUES(" + playlistID + "," + trackID + "," + offlineAvailable + ")");
        mySQLConnector.updateSomethingInDatabase(query);
    }

}
