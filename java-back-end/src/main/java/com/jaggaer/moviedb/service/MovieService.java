package com.jaggaer.moviedb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaggaer.moviedb.dao.PostgresDao;
import com.jaggaer.moviedb.model.Movie;

@Service
public class MovieService {

    private PostgresDao dao;

    @Autowired
    public MovieService(PostgresDao dao) {
        this.dao = dao;
    }

    public List<Movie> getAllMovies() {
        return this.dao.getAllMovies();
    }

    public Optional<Movie> getMovie(Integer id) {
        return this.dao.getMovieById(id);
    }

    public void deleteMovie(Integer id){
        this.dao.deleteMovieById(id);
    }

    public void saveMovie(Movie movie) {

        if (movie.getId()>0) {
            this.dao.updateMovie(movie);
        }else{
            this.dao.createMovie(movie);
        }
    }

	public List<Movie> getAllMovies(Integer genreId) {
        return this.dao.getAllMoviesByGenre(genreId);
	}
}
