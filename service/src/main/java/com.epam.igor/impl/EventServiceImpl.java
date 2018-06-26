package com.epam.igor.impl;

import com.epam.igor.api.EventService;
import com.epam.igor.dao.api.EventDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;
import com.epam.igor.exception.ServiceException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    @Inject
    public void setEventDao(EventDao eventDao){
        this.eventDao = eventDao;
    }

    @Override
    public void createEvent(Event event) throws ServiceException {
        try {
            eventDao.create(event);
        } catch (DaoException e){
            throw new ServiceException("Cannot save event", e);
        }
    }

    @Override
    public void editEvent(Event event) throws ServiceException {
        try {
            eventDao.edit(event);
        } catch (DaoException e){
            throw new ServiceException("Cannot edit event", e);
        }
    }

    @Override
    public List<Event> getAll() throws ServiceException {
        try {
            return eventDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Couldn't load event list", e);
        }
    }
}
