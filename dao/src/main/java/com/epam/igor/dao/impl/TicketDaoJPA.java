package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.TicketDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Ticket;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;

@Dependent
public class TicketDaoJPA implements TicketDao, Serializable {

    private static final long serialVersionUID = 7304243809121174813L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ticket createTicket(Ticket ticket) throws DaoException {
        try{
            entityManager.persist(ticket);
        } catch (PersistenceException e){
            throw  new DaoException("Not enough info to create ticket", e);
        }
        return ticket;
    }
}