package org.superbiz.moviefun.movies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.moviesapi.MovieFixtures;

import java.util.Map;

@Controller
public class MovieController {

    private final MoviesRepository moviesRepository;
    private final MovieFixtures movieFixtures;


    public MovieController(MoviesRepository moviesRepository, MovieFixtures movieFixtures) {
        this.moviesRepository = moviesRepository;
        this.movieFixtures = movieFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (Movie movie : movieFixtures.load()) {
            moviesRepository.addMovie(movie);
        }


        model.put("movies", moviesRepository.getMovies());

        return "setup";
    }
}
