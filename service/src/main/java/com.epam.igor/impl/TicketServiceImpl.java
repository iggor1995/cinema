package com.epam.igor.impl;

import com.epam.igor.api.TicketService;
import com.epam.igor.dao.api.TicketDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Ticket;
import com.epam.igor.exception.ServiceException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;

    @Inject
    public void setTicketDao(TicketDao ticketDao){this.ticketDao = ticketDao;}

    @Override
    public void createTicket(Ticket ticket) throws ServiceException {
        try {
            ticketDao.createTicket(ticket);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create Ticket", e);
        }
    }
}
