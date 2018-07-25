package org.superbiz.moviefun.movies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesRestController {

    MoviesRepository moviesRepository;

    public MoviesRestController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/count")
    public int count(
            @RequestParam(value = "field", required = false) String field,
            @RequestParam(value = "searchTerm", required = false) String searchTerm
    ){
        if (field == null && searchTerm == null){
            return moviesRepository.countAll();
        }else{
            return moviesRepository.count(field,searchTerm);
        }
    }

    @GetMapping
    public List<Movie> find(
            @RequestParam(value = "firstResult", required = false) Integer firstResult,
            @RequestParam(value = "maxResults", required = false) Integer maxResults,
            @RequestParam(value = "field", required = false) String field,
            @RequestParam(value = "searchTerm", required = false) String searchTerm
    ){
        if (firstResult != null && maxResults != null){
            return moviesRepository.findAll(firstResult,maxResults);
        }else if (field != null && searchTerm != null){
            return moviesRepository.findRange(field,searchTerm,firstResult,maxResults);
        }
        else {
            return moviesRepository.getMovies();
        }
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        moviesRepository.addMovie(movie);
    }

    @PutMapping
    public void updateMovie(@RequestBody Movie movie){
        moviesRepository.updateMovie(movie);
    }

    @DeleteMapping
    public void deleteMovie(@RequestBody Movie movie) {
        moviesRepository.deleteMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieId(@PathVariable Long id){
        moviesRepository.deleteMovieId(id);
    }
    @DeleteMapping("/clean")
    public void deleteMovieId(){
        moviesRepository.clean();
    }



}
