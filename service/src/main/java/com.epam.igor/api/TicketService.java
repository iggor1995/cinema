package com.epam.igor.api;


import com.epam.igor.entity.Ticket;
import com.epam.igor.exception.ServiceException;

public interface TicketService {

    void createTicket(Ticket ticket) throws ServiceException;
}
