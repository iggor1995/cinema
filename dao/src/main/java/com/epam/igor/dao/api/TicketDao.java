package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Ticket;

import java.util.List;


public interface TicketDao {

    Ticket createTicket(Ticket ticket) throws DaoException;

    List<Ticket> getAll() throws DaoException;
}
