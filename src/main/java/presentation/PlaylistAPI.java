package presentation;

import domain.Playlist;
import presentation.dto.*;
import service.ActiveUser;
import service.LoginService;
import service.PlaylistService;
import service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistAPI {
    @Inject
    PlaylistService playlistService;
    @Inject
    LoginService loginService;
    @Inject
    TrackService trackService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistsResponse getPlaylists(@QueryParam("token") String token) {
        ActiveUser user = loginService.getUser(token);
        if(user != null) {
            List<Playlist> playlists = playlistService.getPlaylists();
            PlaylistsResponse playlistsResponse = new PlaylistsResponse();
            List<PlaylistDTO> playlistDTOs = new ArrayList<>();
            for(Playlist playlist : playlists){
                playlistDTOs.add(new PlaylistDTO(playlist, user));
            }
            playlistsResponse.setPlaylists(playlistDTOs);
            int length = 0;
            for(Playlist playlist : playlists){
                trackService.fillTracksByPlaylist(playlist);
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
        if(loginService.isViableToken(token)) {
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
        if(loginService.isViableToken(token)){
            String userName = loginService.getUser(token).getUserName();
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
        if(loginService.isViableToken(token)){
            String userName = loginService.getUser(token).getUserName();
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
        if(loginService.isViableToken(token)){
            Playlist playlist = playlistService.getPlaylist(id);
            trackService.fillTracksByPlaylist(playlist);
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
        if(loginService.isViableToken(token)){
            Playlist playlist = playlistService.getPlaylist(playlistID);
            trackService.deleteTrackByPlaylist(playlistID, trackID);
            trackService.fillTracksByPlaylist(playlist);
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
        if(loginService.isViableToken(token)){
            Playlist playlist = playlistService.getPlaylist(playlistID);
            trackService.addTrack(playlist.getId(), trackRequest.getId(), trackRequest.isOfflineAvailable());
            trackService.fillTracksByPlaylist(playlist);
            return new TrackResponse(playlist.getTracks());
        }
        return null;
    }
}
