package org.superbiz.moviefun.albumsapi;

import org.springframework.web.client.RestOperations;
import org.superbiz.moviefun.moviesapi.MovieInfo;

import java.util.ArrayList;
import java.util.List;

public class AlbumsClient {
    private final RestOperations restOperations;
    private final String albumServerEndpoint;

    public AlbumsClient(String albumServerEndpoint,RestOperations restOperations) {
        this.restOperations = restOperations;
        this.albumServerEndpoint = albumServerEndpoint;
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumServerEndpoint, album,AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumServerEndpoint+"/"+id,AlbumInfo.class);

    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.getForObject(
                albumServerEndpoint, new ArrayList<AlbumInfo>().getClass());
    }

    public void deleteAlbum(AlbumInfo album) {
        restOperations.delete(albumServerEndpoint, album);
    }

    public void updateAlbum(AlbumInfo album) {
        restOperations.put(albumServerEndpoint, album);
    }
}
