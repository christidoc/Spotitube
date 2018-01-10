package datasource;

import domain.Playlist;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPlaylistDAO implements PlaylistDAO {
    @Inject
    DBConnector mySQLConnector;
    //DBConnector mySQLConnector = new MySQLConnector();

    public List<Playlist> getAllPlaylists(){
        List<Playlist> playlists = new ArrayList<>();

        String query = ("SELECT * FROM playlist");
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getString("owner"));
                playlists.add(playlist);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return playlists;
    }

    public void deletePlaylist(int playlistID){
        String query = ("DELETE FROM playlist WHERE id=" + playlistID);
        mySQLConnector.updateSomethingInDatabase(query);
    }

    public void addPlaylist(Playlist playlist){
        String query = ("INSERT INTO playlist (name, owner) VALUES('" + playlist.getName() + "', '" + playlist.getOwner() + "');");
        mySQLConnector.updateSomethingInDatabase(query);
    }

    public void updatePlaylist(Playlist playlist){
        String query = ("UPDATE playlist SET name = '" + playlist.getName() + "' WHERE id = " + playlist.getId());
        mySQLConnector.updateSomethingInDatabase(query);
    }

    public Playlist getPlaylist(int playlistID){
        String query = ("SELECT * FROM playlist WHERE id=" + playlistID);
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setOwner(resultSet.getString("owner"));
                return playlist;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
