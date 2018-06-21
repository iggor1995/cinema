package com.epam.igor.entity;


import com.epam.igor.api.TicketService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ManagedBean
@RequestScoped
public class TicketManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";

    @Inject
    private TicketService ticketService;
    private Ticket ticket;

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
}
