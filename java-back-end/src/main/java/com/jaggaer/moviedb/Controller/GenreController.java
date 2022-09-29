package com.jaggaer.moviedb.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaggaer.moviedb.model.Genre;
import com.jaggaer.moviedb.service.GenreService;

@RestController
@RequestMapping("/v1")
public class GenreController {
   
    private GenreService genreService;

    @Autowired
    public void MovieResource(GenreService genreService) {
      this.genreService = genreService;
    }

    @RequestMapping(
      value="/genres", 
      method=RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Map<String, List<Genre>> allMovies() {

      final Map<String, List<Genre>> mapA = new HashMap();
      mapA.put("genres", this.genreService.getAllGenres());
      return mapA;
    }
}
