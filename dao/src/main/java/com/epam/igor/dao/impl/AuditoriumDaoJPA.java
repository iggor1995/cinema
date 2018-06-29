package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.AuditoriumDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Auditorium;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;

@Dependent
public class AuditoriumDaoJPA implements AuditoriumDao, Serializable {

    private static final long serialVersionUID = 7304243809121174813L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Auditorium create(Auditorium auditorium) throws DaoException {
        try {
            entityManager.persist(auditorium);
        } catch (PersistenceException e) {
            throw new DaoException("Not enough information for persist auditorium", e);
        }
        return auditorium;
    }
}
