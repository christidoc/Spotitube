package datasource;

import domain.DomainObject;
import domain.Playlist;
import domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistMapper extends AbstractMapper implements PlaylistDAO{
    static private List<Playlist> playlists;

    protected final String findStatement() {
        return "SELECT * FROM playlist JOIN user ON playlist.owner = user.username WHERE id= ?";
    }

    protected DomainObject load(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        for(Playlist playlist : playlists){
            if(playlist.getId() == id){
                return playlist;
            }
        }
        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setName(rs.getString("name"));
        playlist.setOwner(new User(rs.getString("owner"), rs.getString("password")));
        return playlist;
    }

    public PlaylistMapper() {
        if(playlists == null){
            playlists = new ArrayList<>();
        }
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        //Update playlists
        return playlists;
    }

    @Override
    public void addPlaylist(Playlist playlist) {

    }

    @Override
    public void deletePlaylist(int playlistID) {

    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        //find playlist in playlists
        //if found, update. if not, add
        //update database.
    }

    @Override
    public Playlist getPlaylist(int id) {
        for(Playlist playlist : playlists){
            if(playlist.getId() == id){
                return playlist;
            }
        }
        return (Playlist) abstractFind(id);
        //check database for playlist and add playlist to playlists if found.
    }
}
