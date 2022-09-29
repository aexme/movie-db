package com.jaggaer.moviedb.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Movie {
    
    private final int id;
	private final String title;
	private final String description;
	private final Date release_date;
	private final int runtime;
	private final int rating;
	private final String mpaa_rating;
	private final Date created_at;
	private final Date  updated_at;
	//private final map[int]string `json:"genres"`
	private final String poster;

    public Movie(int id, String title, String description, Date release_date, int runtime,
    int rating, String mpaa_rating, Date created_at, Date updated_at, String poster) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.runtime = runtime;
        this.rating = rating;
        this.mpaa_rating = mpaa_rating;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.poster = poster;
    }

    public Movie(ResultSet resultSet) throws SQLException{
        
        this.id = resultSet.getInt("id");
        this.title = resultSet.getString("title");
        this.description = resultSet.getString("description");
        this.release_date = resultSet.getDate("release_date");
        this.runtime = resultSet.getInt("runtime");
        this.rating = resultSet.getInt("rating");
        this.mpaa_rating = resultSet.getString("mpaa_rating");
        this.created_at = resultSet.getDate("created_at");
        this.updated_at = resultSet.getDate("updated_at"); 
        this.poster = resultSet.getString("poster");
    }

    //public List<Genre> getGenres(){

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getYear() {
        LocalDate localDate = this.release_date.toLocalDate();
        return localDate.getYear();
    }

    public Date getRelease_date() {
        return release_date;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public Integer getRating() {
        return rating;
    }

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
    
    public String getPoster() {
        return poster;
    }
    
    public String toString(){
        return this.title + "("+this.getYear()+")";
    }
}
