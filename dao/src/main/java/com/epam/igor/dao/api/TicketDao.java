package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Ticket;


public interface TicketDao {

    Ticket createTicket(Ticket ticket) throws DaoException;
}
