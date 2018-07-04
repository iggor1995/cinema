package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;

import java.util.List;

public interface EventDao {

    Event create(Event event) throws DaoException;

    List<Event> getAll() throws DaoException;

    Event edit(Event event) throws DaoException;

    List<Event> getByMovieId(long movieId) throws DaoException;
}
