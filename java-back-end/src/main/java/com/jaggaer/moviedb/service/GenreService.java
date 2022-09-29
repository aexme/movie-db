package com.jaggaer.moviedb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaggaer.moviedb.dao.PostgresDao;
import com.jaggaer.moviedb.model.Genre;

@Service
public class GenreService {
    private PostgresDao dao;

    @Autowired
    public GenreService(PostgresDao dao) {
        this.dao = dao;
    }

    public List<Genre> getAllGenres() {
        return this.dao.getAllGenres();
    }
}
