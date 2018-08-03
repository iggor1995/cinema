package com.epam.igor.impl;

import com.epam.igor.api.MovieService;
import com.epam.igor.dao.api.MovieDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Movie;
import com.epam.igor.exception.ServiceException;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
    private MovieDao movieDao;

    @Inject
    public void setEventDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie create(Movie movie) throws ServiceException {
        Movie newMovie;
        try {
            LOGGER.info("Create movie - " + movie);
            newMovie = movieDao.saveMovie(movie);
        } catch (DaoException e) {
            throw new ServiceException("Cannot save movie", e);
        }
        return newMovie;
    }

    @Override
    public List<Movie> getAll() throws ServiceException {
        try {
            return movieDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Couldn't get movies", e);
        }
    }

    @Override
    public Movie getById(long id) throws ServiceException {
        try {
            return movieDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("Couldn't get movie", e);
        }
    }

    @Override
    public void editMovie(Movie movie) throws ServiceException {
        movieDao.edit(movie);
    }
}
