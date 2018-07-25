package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsRestController {

    AlbumsRepository albumsRepository;

    public AlbumsRestController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }


    @GetMapping("/{id}")
    public Album find(@PathVariable  Long id){
        return albumsRepository.find(id);
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
    }

    @PutMapping
    public void updateAlbum(@RequestBody Album album){
        albumsRepository.updateAlbum(album);
    }

    @DeleteMapping
    public void deleteAlbum(@RequestBody Album album) {
        albumsRepository.deleteAlbum(album);
    }

    @GetMapping
    public List<Album> getAlbums(){
        return albumsRepository.getAlbums();
    }

}
