package com.epam.igor.impl;

import com.epam.igor.api.EventService;
import com.epam.igor.dao.api.EventDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);
    private EventDao eventDao;

    @Inject
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event createEvent(Event event) throws ServiceException {
        Event newEvent;
        try {
            LOGGER.info("Create event - " + event);
            newEvent = eventDao.create(event);
        } catch (DaoException e) {
            throw new ServiceException("Cannot save event", e);
        }
        return newEvent;
    }

    @Override
    public void editEvent(Event event) throws ServiceException {
        try {
            LOGGER.info("Edit event - " + event);
            eventDao.edit(event);
        } catch (DaoException e) {
            throw new ServiceException("Cannot edit event", e);
        }
    }

    @Override
    public List<Event> getByMovieId(long movieId) throws ServiceException {
        List<Event> events;
        try {
            events = eventDao.getByMovieId(movieId);
        } catch (DaoException e) {
            throw new ServiceException("Couldn't get event list");
        }
        return events;
    }

    @Override
    public List<Event> getAll() throws ServiceException {
        try {
            LOGGER.info("Get all events");
            return eventDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Couldn't load event list", e);
        }
    }
}
