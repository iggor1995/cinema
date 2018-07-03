package com.epam.igor.api;

import com.epam.igor.entity.Movie;
import com.epam.igor.exception.ServiceException;

import java.util.List;

public interface MovieService {

    void create(Movie movie) throws ServiceException;

    List<Movie> getAll() throws ServiceException;

    Movie getById(long id) throws ServiceException;

    void editMovie(Movie movie) throws ServiceException;
}
