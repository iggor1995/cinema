package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Movie;

import java.util.List;

public interface MovieDao {

    Movie saveMovie(Movie movie) throws DaoException;

    List<Movie> getAll() throws DaoException;

    Movie getById(long id) throws DaoException;

    void edit(Movie movie);
}
