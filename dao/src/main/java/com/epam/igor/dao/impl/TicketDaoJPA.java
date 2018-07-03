package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.TicketDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Ticket;
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
public class TicketDaoJPA implements TicketDao, Serializable {

    private static final long serialVersionUID = 7304243809121174813L;
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoJPA.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ticket createTicket(Ticket ticket) throws DaoException {
        try {
            LOGGER.info("Save ticket to DB - " + ticket);
            entityManager.persist(ticket);
        } catch (PersistenceException e) {
            throw new DaoException("Not enough info to create ticket", e);
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() throws DaoException {
        LOGGER.info("Retrieve tickets");
        Query query = entityManager.createQuery("From Ticket", Ticket.class);
        return query.getResultList();
    }
}
