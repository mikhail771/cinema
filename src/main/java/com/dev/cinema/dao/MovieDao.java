package com.dev.cinema.dao;

import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Movie;
import java.util.List;

@Dao
public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
