package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.EventDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event create(Event event) throws DaoException {
        try{
            entityManager.persist(event);
        } catch (PersistenceException e){
            throw new DaoException("Cannot save new event", e);
        }
        return event;
    }

    @Override
    public List<Event> getAll() throws DaoException {
        Query query = entityManager.createQuery("From Event", Event.class);
        return query.getResultList();
    }

}
