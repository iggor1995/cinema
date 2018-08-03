package com.epam.igor.api;


import com.epam.igor.entity.Event;
import com.epam.igor.entity.Ticket;
import com.epam.igor.exception.ServiceException;

import java.util.List;

public interface TicketService {

    Ticket createTicket(Ticket ticket) throws ServiceException;

    List<String> getAvailableSeats(Event event) throws ServiceException;
}
