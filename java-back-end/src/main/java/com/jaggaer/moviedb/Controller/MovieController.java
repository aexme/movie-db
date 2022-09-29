package com.jaggaer.moviedb.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaggaer.moviedb.model.Movie;
import com.jaggaer.moviedb.service.MovieService;

@RestController
@RequestMapping("/v1")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public void MovieResource(MovieService movieService) {
      this.movieService = movieService;
    }

    @RequestMapping(
      value="/movies", 
      method=RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Map<String, List<Movie>> allMovies() {

      final Map<String, List<Movie>> mapA = new HashMap();
      mapA.put("movies", this.movieService.getAllMovies());
      return mapA;
    }

    @RequestMapping(
      value="/movies/{genreId}", 
      method=RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Map<String, List<Movie>> moviesByGenre(@PathVariable("genreId") Integer genreId) {

      final Map<String, List<Movie>> mapA = new HashMap();
      mapA.put("movies", this.movieService.getAllMovies(genreId));
      return mapA;
    }

    @RequestMapping(
      value="/movie/{id}", 
      method=RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Map<String, Optional<Movie>> movie(@PathVariable("id") Integer id) {

        final Map<String, Optional<Movie>> mapA = new HashMap();
        mapA.put("movie", this.movieService.getMovie(id));
        return mapA;
    }
}
