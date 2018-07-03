package com.epam.igor.entity;

import com.epam.igor.api.EventService;
import com.epam.igor.api.MovieService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class ListManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListManager.class);

    @Inject
    private EventService eventService;
    @Inject
    private MovieService movieService;

    /**
     * Get all events
     */
    public List<Event> getEvents() throws ServiceException {
        return eventService.getAll();
    }

    /**
     * Get all movies
     */
    public List<Movie> getMovies() throws ServiceException {
        return movieService.getAll();
    }
}
