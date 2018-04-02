package datasource;

import domain.DomainObject;
import domain.Playlist;
import domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistMapper extends AbstractMapper implements PlaylistDAO{
    static private List<Playlist> playlists;

    protected final String findStatement() {
        return "SELECT * FROM playlist JOIN user ON playlist.owner = user.username WHERE id= ?";
    }

    protected  final String findAllStatement() {
        return "SELECT * FROM playlist";
    }

    protected final String insertStatement() {
        return "INSERT INTO playlist VALUES (?, ?)";
    }

    protected final String deleteStatement() {

    }

    public PlaylistMapper() {
        if(playlists == null){
            playlists = new ArrayList<>();
        }
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
        playlists.add(playlist);
        return playlist;
    }

    protected List<DomainObject> loadAll (ResultSet rs) throws SQLException {
        List<DomainObject> playlists = new ArrayList<>();
        try {
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id"));
                playlist.setName(rs.getString("name"));
                //playlist.setOwner(new User(rs.getString("owner"), rs.getString("password")));
                playlists.add(playlist);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return playlists;
    }

    protected void doInsert (DomainObject subject, PreparedStatement insertStatement) throws SQLException {
            Playlist playlist = (Playlist) subject;
            insertStatement.setString(1, playlist.getName());
            insertStatement.setString(2, playlist.getOwner().getUserName());
            playlists.add(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> returnList = new ArrayList<>();
        for(Object o : abstractFindAll()){
            try{
                returnList.add((Playlist) o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        insert(playlist);
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
    }
}
