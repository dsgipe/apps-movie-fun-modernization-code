package org.superbiz.moviefun.moviesapi;

import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class MoviesClient{


    private final RestOperations restOperations;
    private final String movieServerEndpoint;

    public MoviesClient(String registrationServerEndpoint,RestOperations restOperations) {
        this.restOperations = restOperations;
        this.movieServerEndpoint = registrationServerEndpoint;
    }

    public void addMovie(MovieInfo movie) {restOperations.postForEntity(movieServerEndpoint, movie,MovieInfo.class);}

    public void updateMovie(MovieInfo movie) {restOperations.put(movieServerEndpoint, movie);}

    public void clean(){restOperations.delete(movieServerEndpoint + "/clean");}

    public void deleteMovie(MovieInfo movie) {restOperations.delete(movieServerEndpoint, movie);}

    public void deleteMovieId(long id) {restOperations.delete(movieServerEndpoint + "/" + id);}

    public int countAll() {
        return restOperations.getForObject(
                movieServerEndpoint + "/count", Integer.class);
    }

    public int count(String field, String searchTerm) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(movieServerEndpoint+"/count")
                .queryParam("field", field)
                .queryParam("searchTerm", searchTerm);
        return restOperations.getForObject(builder.toUriString(), Integer.class);
    }

    public List<MovieInfo> getMovies() {
        return restOperations.getForObject(
                movieServerEndpoint, new ArrayList<MovieInfo>().getClass());
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(movieServerEndpoint)
                .queryParam("firstResult", firstResult)
                .queryParam("maxResults", maxResults);

        return restOperations.exchange(
                builder.toUriString(), GET,null,new ArrayList<MovieInfo>().getClass()).getBody();
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(movieServerEndpoint)
                .queryParam("firstResult", firstResult)
                .queryParam("maxResults", maxResults)
                .queryParam("field", field)
                .queryParam("searchTerm", searchTerm);
        return restOperations.exchange(
                builder.toUriString(), GET,null,new ArrayList<MovieInfo>().getClass()).getBody();
    }


}
