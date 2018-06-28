package com.epam.igor.entity;


import com.epam.igor.api.TicketService;
import com.epam.igor.api.UserService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ManagedBean
@RequestScoped
public class TicketManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";

    @Inject
    private TicketService ticketService;

    @Inject
    private UserService userService;

    private Ticket ticket;
    private String seat;

    @Produces
    @Named
    public Ticket getTicket() {
        return ticket;
    }

    @PostConstruct
    public void init() {
        this.ticket = new Ticket();
    }

    public String saveTicket(Ticket ticket){
        try {
            ticketService.createTicket(ticket);
        } catch (ServiceException e) {
            LOGGER.error("Cannot save ticket");
        }
        return HOME;
    }

    public String buyTicket(Event event, String name){
        LOGGER.info("Buy action. Event - " + event + " userName - " + name);
        try {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setSeat(this.seat);
            ticket.setEventId(event.getId());
            User curUser = userService.getUserByName(name);
            ticket.setUserId(curUser.getId());

            if(userService.chargeUser(curUser, event.getBasePrice()))
                ticketService.createTicket(ticket);
            LOGGER.debug("not enough money");
        } catch (ServiceException e) {
            LOGGER.error("cannot save ticket");
        }
        return "/pages/home?faces-redirect=true";
    }

    public List<String> getAvailableSeats(Event event){
        try {
            return ticketService.getAvailableSeats(event);
        } catch (ServiceException e) {
            LOGGER.error("Couldn't count available seats");
        }
        return null;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
