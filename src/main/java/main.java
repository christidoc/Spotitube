import datasource.MySQLPlaylistDAO;
import domain.Playlist;

/**
 * Om snel iets te proberen zonder tests te hoeven schrijven :o
 */
public class main {
    public static void main (String [] args){
        MySQLPlaylistDAO mySQLPlaylistDAO = new MySQLPlaylistDAO();
        Playlist playlist = mySQLPlaylistDAO.getPlaylist(1);
            System.out.println(playlist.getId() + " username: " + playlist.getOwner().getUserName() + " en password: " + playlist.getOwner().getPassword());
    }
}
