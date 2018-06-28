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
import java.util.Arrays;
import java.util.List;

@ManagedBean
@RequestScoped
public class TicketManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";

    @Inject
    private TicketService ticketService;
    private Ticket ticket;
    private String[] favFood3;

    public String[] getFavFood3() {
        return favFood3;
    }

    public void setFavFood3(String[] favFood3) {
        this.favFood3 = favFood3;
        for(String s : favFood3){
            System.out.println(s);
        }
    }

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

    public List<String> getAvailableSeats(Event event){
        try {
            return ticketService.getAvailableSeats(event);
        } catch (ServiceException e) {
            LOGGER.error("Couldn't count available seats");
        }
        return null;
    }

}
