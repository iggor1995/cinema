package com.epam.igor.impl;

import com.epam.igor.api.TicketService;
import com.epam.igor.dao.api.TicketDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;
import com.epam.igor.entity.Ticket;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);
    private TicketDao ticketDao;

    @Inject
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void createTicket(Ticket ticket) throws ServiceException {
        try {
            LOGGER.info("Create ticket - " + ticket);
            ticketDao.createTicket(ticket);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create Ticket", e);
        }
    }

    @Override
    public List<String> getAvailableSeats(Event event) throws ServiceException {
        LOGGER.info("Getting available seats");
        List<Ticket> tickets;
        List<String> availableSeats = createSeatList(event.getAuditorium().getSeatsNumber());
        try {
            tickets = ticketDao.getAll();
            if (tickets != null) {
                availableSeats = excludeUserSeats(availableSeats, tickets, event.getId());
            }
        } catch (DaoException e) {
            throw new ServiceException("couldn't extract tickets list");
        }
        return availableSeats;
    }

    private List<String> createSeatList(int seatsNumber) {
        List<String> allSeats = new ArrayList<>();
        for (int i = 1; i <= seatsNumber; i++) {
            allSeats.add(i + "");
        }
        return allSeats;
    }

    private List<String> excludeUserSeats(List<String> availableSeats, List<Ticket> tickets, long eventId) {
        for (Ticket ticket : tickets) {
            if (ticket.getEventId().equals(eventId)) {
                String[] usedSeats = ticket.getSeat().split(",");
                availableSeats.removeAll(Arrays.asList(usedSeats));
            }
        }
        return availableSeats;
    }
}
