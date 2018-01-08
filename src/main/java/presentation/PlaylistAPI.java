package presentation;

import domain.Playlist;
import domain.Token;
import presentation.dto.PlaylistsRequest;
import presentation.dto.PlaylistsResponse;
import presentation.dto.TrackRequest;
import presentation.dto.TrackResponse;
import service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/playlists")
public class PlaylistAPI {
    @Inject
    PlaylistService playlistService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse getPlaylists(@QueryParam("token") String token) {
        if(Token.isViableToken(token)) {
            List<Playlist> playlists = playlistService.getPlaylists();
            PlaylistsResponse playlistsResponse = new PlaylistsResponse();
            playlistsResponse.setPlaylists(playlists);
            int length = 0;
            for(Playlist playlist : playlists){
                playlist.fillTracks();
                length += playlist.getLength();
            }
            playlistsResponse.setLength(length);
            return playlistsResponse;
        }
        return null;
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse deletePlaylist(@PathParam("id") int playlistID,
                                            @QueryParam("token") String token){
        if(Token.isViableToken(token)) {
            playlistService.deletePlaylist(playlistID);

            return getPlaylists(token);
        }
        return null;
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse addPlaylist (@QueryParam("token") String token,
                                          PlaylistsRequest playlistsRequest){
        if(Token.isViableToken(token)){
            String userName = Token.getUser(token).getUserName();
            playlistService.addPlaylist(new Playlist(playlistsRequest.getId(), playlistsRequest.getName(), userName, playlistsRequest.getTracks()));

            return getPlaylists(token);
        }
        return null;
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse editPlaylist (@PathParam("id") int playlistID,
                                           @QueryParam("token") String token,
                                           PlaylistsRequest playlistsRequest) {
        if(Token.isViableToken(token)){
            String userName = Token.getUser(token).getUserName();
            playlistService.editPlaylist(new Playlist(playlistsRequest.getId(), playlistsRequest.getName(), userName, playlistsRequest.getTracks()));

            return getPlaylists(token);
        }
        return null;
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TrackResponse getTracksByPlaylist( @PathParam("id") int id,
                                              @QueryParam("token") String token){
        if(Token.isViableToken(token)){
            Playlist playlist = playlistService.getPlaylist(id);
            playlist.fillTracks();
            return new TrackResponse(playlist.getTracks());
        }
        return null;
    }

    @Path("/{playlistID}/tracks/{trackID}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public TrackResponse removeTrack( @PathParam("playlistID") int playlistID,
                                      @PathParam("trackID") int trackID,
                                      @QueryParam("token") String token) {
        if(Token.isViableToken(token)){
            Playlist playlist = playlistService.getPlaylist(playlistID);
            playlist.fillTracks();
            playlist.deleteTrack(trackID);
            return new TrackResponse(playlist.getTracks());
        }
        return null;
    }

    @Path("/{playlistID}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TrackResponse addTrack( @PathParam("playlistID") int playlistID,
                                      @QueryParam("token") String token,
                                      TrackRequest trackRequest) {
        if(Token.isViableToken(token)){
            Playlist playlist = playlistService.getPlaylist(playlistID);
            playlist.addTrack(trackRequest.getId(), trackRequest.isOfflineAvailable());
            return new TrackResponse(playlist.getTracks());
        }
        return null;
    }
}
