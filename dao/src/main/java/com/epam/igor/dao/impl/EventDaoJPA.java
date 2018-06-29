package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.EventDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;
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
public class EventDaoJPA implements EventDao, Serializable {

    private static final long serialVersionUID = 7304243809121174813L;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventDaoJPA.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event create(Event event) throws DaoException {
        try{
            LOGGER.info("Save event to db");
            entityManager.persist(event);
        } catch (PersistenceException e){
            throw new DaoException("Cannot save new event", e);
        }
        return event;
    }

    @Override
    public Event edit(Event event) throws DaoException {
        try{
            LOGGER.info("Edit event in db");
            entityManager.merge(event);
        } catch (PersistenceException e){
            throw new DaoException("Cannot save new event", e);
        }
        return event;
    }

    @Override
    public List<Event> getAll() throws DaoException {
        LOGGER.info("Retrieve events from db");
        Query query = entityManager.createQuery("From Event", Event.class);
        return query.getResultList();
    }

}
