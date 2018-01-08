package presentation;

import domain.Playlist;
import domain.User;
//import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import presentation.dto.LoginRequest;
import presentation.dto.LoginResponse;
import presentation.dto.PlaylistsResponse;
import service.TokenService;
import service.LoginService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Christiaan on 12-10-2017.
 * Moet waarschijnlijk in meerdere klassen. één playlist, één login en één track en dan gaat deze weg.
 */
//@CrossOriginResourceSharing(
//        allowAllOrigins = true,
//        allowOrigins = { "http://ci.icaprojecten.nl"}
//)
@Path("/")
public class REST {
    LoginService loginService = new LoginService();
    TokenService tokenService = new TokenService();
//    @GET
//    @Produces("text/plain")
//    public String getClichedMessage() {
//        return "Hello World";
//    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Hello(){
        return "Basic Path doet het.";
    }

    //Test om te kijken of Path goed werkt.
    @Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse playlists2(){
        LoginResponse response = new LoginResponse("username", "token");
        return response;
    }

    //LOGIN
//    @Path("/login")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public LoginResponse login(LoginRequest loginRequest) {
//        LoginResponse response;
//        User user = loginService.getUserByPassword(loginRequest.getUser(), loginRequest.getPassword());
//        if(user != null){
//            response = new LoginResponse(loginRequest.getUser(), user.getToken());
//        }
//        else {
//            return null;
//        }
//        return response;
//    }

//    @Path("/login")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public LoginResponse login(LoginRequest loginRequest) {
//        LoginService loginService = new LoginService();
//        User user = loginService.getUserByPassword(loginRequest.getUser(), loginRequest.getPassword());
//        if(user != null){
//            loginService.addUser(user);
//            return new LoginResponse(user.getUserName(), user.getToken());
//        }
//        else{
//            return null;
//        }
//    }

    //Get all playlists
    @Path("/playlists")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse getPlaylists(@QueryParam("token") String token){
        return null;
    }

    //Delete a playlist
    @Path("/playlists/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse deletePlaylist(@PathParam("id") int playlistID,
                                            @QueryParam("token") String token){
        return null;
    }

    //Add a playlist
    @Path("/playlists")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse addPlaylist (@QueryParam("token") String token,
                                          Playlist newPlaylist){
        return null;
    }

    @Path("/playlists/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse editPlaylist (@QueryParam("token") String token,
                                          Playlist newPlaylist){
        return null;
    }
}