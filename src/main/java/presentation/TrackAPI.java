package presentation;

import domain.Playlist;
import domain.Token;
import presentation.dto.TrackResponse;
import service.PlaylistService;

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

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TrackResponse getTracks(@QueryParam("forPlaylist") int playlistID,
                                   @QueryParam("token") String token) {
        if(Token.isViableToken(token)) {
            Playlist playlist = playlistService.getPlaylist(playlistID);
            playlist.fillTracks();
            return new TrackResponse(playlist.getAddableTracks());
        }
        return null;
    }
}
