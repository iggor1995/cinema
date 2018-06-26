package com.epam.igor.entity;

import com.epam.igor.api.EventService;
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
public class EventManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";

    @Inject
    private EventService eventService;
    private Event event;

    @Produces
    @Named
    public Event getEvent() {
        return event;
    }

    @PostConstruct
    public void init() {

        this.event = new Event();
    }

    public String saveEvent(Event event) {
        try {
            eventService.createEvent(event);
        } catch (ServiceException e) {
            LOGGER.error("Cannot save user");
        }
        return HOME;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
