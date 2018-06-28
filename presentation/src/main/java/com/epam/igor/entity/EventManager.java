package com.epam.igor.entity;

import com.epam.igor.api.EventService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ManagedBean
@SessionScoped
public class EventManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";
    private static final String EDIT_EVENT = "/pages/admin/event/event-edit?faces-redirect=true";
    private static final String SHOW_EVENT = "/pages/event-page?faces-redirect=true";
    private static final String BOOK_EVENT_PAGE = "/pages/user/book-ticket?faces-redirect=true";

    @Inject
    private EventService eventService;
    private Event event;

    @Produces
    @Named
    public Event getEvent() {
        return event;
    }

    /**
     * initialize event
     */
    @PostConstruct
    public void init() {
        this.event = new Event();
    }

    /**
     * Save new event
     * @param event - filled from the form
     * @return - return home page
     */
    public String saveEvent(Event event) {
        try {
            eventService.createEvent(event);
        } catch (ServiceException e) {
            LOGGER.error("Cannot save event");
        }
        return HOME;
    }

    public String saveEditEvent(Event event) {
        try {
            eventService.editEvent(event);
        } catch (ServiceException e) {
            LOGGER.error("Cannot save event");
        }
        return HOME;
    }

    /**
     * Edit current event
     * @param event - has to be edited
     * @return - return event-edit page
     */
    public String editEvent(Event event){
        this.event = event;
        return EDIT_EVENT;
    }

    public String showEvent(Event event){
        this.event = event;
        return SHOW_EVENT;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String showBookTicketPage(Event event){
        this.event = event;
        return BOOK_EVENT_PAGE;
    }
}
