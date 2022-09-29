package com.jaggaer.moviedb.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaggaer.moviedb.model.Movie;
import com.jaggaer.moviedb.service.MovieService;

@EnableWebSecurity
@RestController
@RequestMapping("/v1")
public class AdminController {

    private MovieService movieService;

    @Autowired
    public void MovieResource(MovieService movieService) {
      this.movieService = movieService;
    }

    @RequestMapping(
      value="/admin/editmovie",
      method=RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Map<String, String> addmovie(@RequestBody Movie movie) {
        this.movieService.saveMovie(movie);

        final Map<String, String> mapA = new HashMap();
        mapA.put("response", "ok");
        return mapA;
    }  
    
    @RequestMapping(
      value="/admin/deletemovie/{id}", 
      method=RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Map<String, String> deleteMovie(@PathVariable("id") Integer id) {

        this.movieService.deleteMovie(id);

        final Map<String, String> mapA = new HashMap();
        mapA.put("response", "ok");
        return mapA;
    }
}
