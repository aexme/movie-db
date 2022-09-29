package com.jaggaer.moviedb.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jaggaer.moviedb.model.Genre;
import com.jaggaer.moviedb.model.Movie;

@Repository
public class PostgresDao{

    public List<Movie> getAllMoviesByGenre(int genreId) {
        List<Movie> movies = new ArrayList<Movie>();

        try {
            Connection db = getDb();
            PreparedStatement statement = db.prepareStatement("SELECT * FROM movies where id in (select movie_id from movies_genres where genre_id = ?)");

            statement.setInt(1, genreId);

            ResultSet resultSet = statement.executeQuery();

            while ( resultSet.next() ) {
                final Movie movie = new Movie(resultSet);
                movies.add(movie);
            }

            resultSet.close();
            statement.close();
            db.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } 

        return movies;
    }

    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<Movie>();

        try {

            Connection db = getDb();
            Statement stmt = db.createStatement();
            ResultSet resultSet = stmt.executeQuery( "SELECT * FROM movies;" );

            while ( resultSet.next() ) {
                final Movie movie = new Movie(resultSet);
                movies.add(movie);
            }

            resultSet.close();
            stmt.close();
            db.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } 

        return movies;
    }

    private Connection getDb() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/go_movies","postgres", "postgres");
        db.setAutoCommit(false);

        return db;
    }

    public Optional<Movie> getMovieById(Integer movieId) {
        
        try {

            Connection db = getDb();
            Statement statement = db.createStatement( );
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movies where id= "+movieId+";");

            if (resultSet.next() != false) {
                
                final Movie movie = new Movie(resultSet);
                resultSet.close();
                statement.close();
                db.close();

                return Optional.of(movie);
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } 

        return Optional.empty();
    }

    public boolean updateMovie(Movie movie) {
        try {

            Connection db = getDb();
            PreparedStatement statement = db.prepareStatement("UPDATE MOVIES set "
                                                                +"title=?," 
                                                                +"description=?," 
                                                                +"year=?," 
                                                                +"release_date=?," 
                                                                +"runtime=?,"
                                                                +"rating=?," 
                                                                +"mpaa_rating=?,"
                                                                +"created_at=?," 
                                                                +"updated_at=?"
                                                                +" WHERE id =?;");

            this.setFields(statement, movie);
            statement.setInt(10, movie.getId());
            
            statement.executeUpdate();

            statement.close();
            db.commit();
            db.close();

            return true;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } 

        return false;
    }

    public boolean deleteMovieById(Integer movieId) {
        
        try {
            Connection db = getDb();
            PreparedStatement statement = db.prepareStatement("DELETE FROM MOVIES WHERE id = ? ");
            statement.setInt(1, movieId);

            statement.executeUpdate();

            statement.close();
            db.commit();
            db.close();

            return true;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } 

        return false;
    }

    public boolean createMovie(Movie movie) {
        
        try {

            Connection db = getDb();
            PreparedStatement statement = db.prepareStatement("INSERT INTO MOVIES "
                                                            + "(title, description, year, release_date, runtime, rating, mpaa_rating, created_at, updated_at) "
                                                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            this.setFields(statement, movie);
            statement.executeUpdate();

            statement.close();
            db.commit();
            db.close();

            return true;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } 

        return false;
    }
    
    private void setFields(PreparedStatement statement, Movie movie) throws SQLException{

        Date date = new Date();
        java.sql.Date now = new java.sql.Date(date.getTime());

        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getDescription());
        statement.setInt(3, movie.getYear());
        statement.setDate(4, movie.getRelease_date());
        statement.setInt(5, movie.getRuntime());
        statement.setInt(6, movie.getRating());
        statement.setString(7, movie.getMpaa_rating());
        statement.setDate(8, now);
        statement.setDate(9, now);
    }

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<Genre>();

        try {

            Connection db = getDb();
            Statement stmt = db.createStatement();
            ResultSet resultSet = stmt.executeQuery( "SELECT * FROM genres;" );

            while ( resultSet.next() ) {
                final Genre genre = new Genre(resultSet);
                genres.add(genre);
            }

            resultSet.close();
            stmt.close();
            db.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        } 

        return genres;
    }
}
