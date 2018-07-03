package com.epam.igor.entity;

import com.epam.igor.api.EventService;
import com.epam.igor.api.MovieService;
import com.epam.igor.exception.ServiceException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;

@ManagedBean
@SessionScoped
public class EventManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";
    private static final String EDIT_EVENT = "/pages/admin/event/event-edit?faces-redirect=true";
    private static final String SHOW_EVENT = "/pages/event-page?faces-redirect=true";
    private static final String MANAGE_EVENTS = "/pages/admin/events-manage?faces-redirect=true";
    private static final String BOOK_EVENT_PAGE = "/pages/user/book-ticket?faces-redirect=true";

    @Inject
    private EventService eventService;
    @Inject
    private MovieService movieService;
    private Event event;

    @Produces
    @Named
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * initialize event
     */
    @PostConstruct
    public void init() {
        this.event = new Event();
    }

    /**
     * Method returns stream of the image to display it on page
     */
    public StreamedContent getImage() throws ServiceException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            byte[] image = movieService.getById(event.getMovieId()).getImage();

            return new DefaultStreamedContent(new ByteArrayInputStream(image),
                    "image/png");
        }
    }

    /**
     * Save new event
     *
     * @param event - filled from the form
     * @return - return home page
     */
    public String saveEvent(Event event) {
        try {
            eventService.createEvent(event);
        } catch (ServiceException e) {
            LOGGER.error("Cannot save event");
        }
        return MANAGE_EVENTS;
    }

    /**
     * Method saves edited event
     *
     * @param event - edited object
     */
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
     *
     * @param event - has to be edited
     * @return - return event-edit page
     */
    public String editEvent(Event event) {
        this.event = event;
        return EDIT_EVENT;
    }

    /**
     * Set event, and return address of the event page
     *
     * @param event - event to show
     * @return event address
     */
    public String showEvent(Event event) {
        this.event = event;
        return SHOW_EVENT;
    }

    /**
     * return booking page
     *
     * @param event set event to book
     * @return book address
     */
    public String showBookTicketPage(Event event) {
        this.event = event;
        return BOOK_EVENT_PAGE;
    }
}
