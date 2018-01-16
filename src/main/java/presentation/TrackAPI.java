package presentation;

import domain.Playlist;
import presentation.dto.TrackResponse;
import service.LoginService;
import service.PlaylistService;
import service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Christiaan on 8-11-2017.
 */
@Path("/tracks")
public class TrackAPI {
    @Inject
    PlaylistService playlistService;
    @Inject
    LoginService loginService;
    @Inject
    TrackService trackService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TrackResponse getTracks(@QueryParam("forPlaylist") int playlistID,
                                   @QueryParam("token") String token) {
        if(loginService.isViableToken(token)) {
            Playlist playlist = playlistService.getPlaylist(playlistID);
            trackService.fillTracksByPlaylist(playlist);
            return new TrackResponse(trackService.getAddableTracksByPlaylist(playlist));
        }
        return null;
    }
}
