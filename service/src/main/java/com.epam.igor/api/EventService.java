package com.epam.igor.api;

import com.epam.igor.entity.Event;
import com.epam.igor.exception.ServiceException;

import java.util.List;

public interface EventService {

    void createEvent(Event event) throws ServiceException;

    List<Event> getAll() throws ServiceException;

    void editEvent(Event event) throws ServiceException;

    List<Event> getByMovieId(long movieId) throws ServiceException;
}
