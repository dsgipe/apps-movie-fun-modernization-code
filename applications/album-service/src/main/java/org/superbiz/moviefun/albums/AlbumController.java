package org.superbiz.moviefun.albums;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.albums.Album;
import org.superbiz.moviefun.albums.AlbumFixtures;
import org.superbiz.moviefun.albums.AlbumsBean;

import java.util.Map;

@Controller
public class AlbumController {


    private final AlbumsBean albumsBean;
    private final AlbumFixtures albumFixtures;

    public AlbumController(AlbumsBean albumsBean, AlbumFixtures albumFixtures) {
        this.albumsBean = albumsBean;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {

        for (Album album : albumFixtures.load()) {
            albumsBean.addAlbum(album);
        }

        model.put("albums", albumsBean.getAlbums());

        return "setup";
    }
}
