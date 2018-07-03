package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.MovieDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Dependent
public class MovieDaoJPA implements MovieDao, Serializable {

    private static final long serialVersionUID = 7304243809121174813L;
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieDaoJPA.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Movie saveMovie(Movie movie) throws DaoException {
        try {
            LOGGER.info("Save movie - " + movie);
            System.out.println(movie.getName() + " " + movie.getDescription() + " " + movie.getReleaseDate() + " " + movie.getImage());
            entityManager.persist(movie);
            entityManager.flush();
        } catch (PersistenceException e) {
            throw new DaoException("Not enough information for persist movie", e);
        }
        return movie;
    }

    @Override
    public List<Movie> getAll() throws DaoException {
        LOGGER.info("Retrieve movies from db");
        Query query = entityManager.createQuery("From Movie", Movie.class);
        return query.getResultList();
    }

    @Override
    public Movie getById(long id) throws DaoException {
        return entityManager.find(Movie.class, id);
    }

    @Override
    public void edit(Movie movie) {
        entityManager.merge(movie);
    }


}
