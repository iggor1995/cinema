package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.EventDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;

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
}
