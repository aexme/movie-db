package com.jaggaer.moviedb.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Genre {
    private final int id;
    private final String genre_name;
    private final Date created_at;
    private final Date updated_at;

    public Genre(int id, String genre_name, Date created_at, Date updated_at) {
        this.id = id;
        this.genre_name = genre_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Genre(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.genre_name = resultSet.getString("genre_name");
        this.created_at = resultSet.getDate("created_at");
        this.updated_at = resultSet.getDate("updated_at");
    }

    public int getId() {
        return id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    } 
}
